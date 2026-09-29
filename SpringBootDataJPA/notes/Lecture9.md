# Notes
1. Manually setting id via builder on a "new" entity makes Hibernate treat it as existing (detached), causing UPDATE instead of INSERT — leads to ObjectOptimisticLockingFailureException/StaleObjectStateException — leave it null so it's treated as transient.
2. Cascade works via Java object graph traversal (parent → child collection), independent of which side actually holds the foreign key. Delete propagation depends on where cascade is configured (usually the business/parent side), not on which side is the JPA owning side. Cascade type helps to remove children when their parents are deleted.
3. Reassigning a FK field (e.g., changing an appointment's doctor) is a simple UPDATE — it does not trigger any cascade delete on the other related entity. Cascade delete only triggers when the actual parent entity itself is deleted (repository.delete()), not on field/reference updates.
4. In JPQL, JOIN navigates through entity relationship fields (Java field names), not raw database column/table names. Which entity goes in FROM (main entity) depends purely on what result you want back — no fixed rule tied to owning/inverse or parent/child side.
5. FetchType.EAGER on entity-level relationships (default for @OneToOne/@ManyToOne) is the main hidden cause of N+1 problems — even after adding JOIN FETCH for one relationship, other EAGER fields still trigger extra queries. Also look for toString()
6. Only one collection (List/bag) can be JOIN FETCHed per query without hitting MultipleBagFetchException — using Set instead of List avoids this issue.
7. Collection-based JOIN FETCH should not be combined with Pageable/pagination — Hibernate logs a warning and loads everything into memory anyway, defeating pagination's purpose.
8. LazyInitializationException on toString() happens when a lazy-loaded field (like an excluded-from-fetch relation) is accessed after the transaction/session has closed — fix by either fetching it explicitly, excluding it via @ToString.Exclude, or keeping the session open with @Transactional (though the latter reintroduces N+1 for that field).

# Details
1. Orphan — a child entity that no longer has any parent referencing it, after the association between parent and child has been removed. orphanRemoval is configured on the parent side of the relationship. When you remove the association from parent to child, the child also becomes orphan.
2. XxxToOne relationships (@OneToOne, @ManyToOne) default to EAGER fetch; XxxToMany relationships (@OneToMany, @ManyToMany) default to LAZY fetch; @OneToOne is almost always eager by default in both directions.
3. At product-based companies, engineers typically write their own JPQL queries explicitly rather than relying heavily on Hibernate's default/derived query behavior.
4. If a patient stays in the system but gets assigned a new insurance, the old insurance becomes orphan (no longer referenced by any patient) and gets deleted via orphan removal.
5. If a patient stays in the system but has their insurance removed entirely (patient with no insurance), the previously linked insurance becomes orphan after this operation.
6. Join operation is a costly and heavy operation — mapped rows shouldn't be pulled unless actually needed (achieved via lazy loading, exclusion, or similar strategies).
7. For XxxToMany relationships, explicitly setting FetchType.EAGER results in 1 query to fetch the "one" side plus N queries to fetch each of the "many" rows — this is the N+1 problem. Hibernate generates exactly this kind of unoptimized query in such scenarios, so fetching strategy shouldn't be left entirely to Hibernate's defaults.
8. Setting FetchType.EAGER at the entity level is almost always a bad idea, especially in bidirectional relationships — loading one entity can end up automatically loading its entire connected object graph.
9. With JOIN FETCH, Hibernate 6 automatically merges duplicate parent objects on its own, so DISTINCT isn't strictly necessary (though adding it causes no harm).
10. To overcome the N+1 problem, JPQL queries should be written explicitly rather than relying on default fetch behavior. Best practice: set all relationships to LAZY by default, and use JOIN FETCH explicitly in each specific query to load exactly what's needed ( also look for toString() ).

# Recap
1. updateInsuranceOfPatient
2. removeInsuranceOfPatient
3. add orphanRemoval attribute (orphanRemoval = true) to required field property
4. JPQL for N+1 optimization

# Annotations / Properties
1. @EntityGraph(attributePaths = {...}) is an alternative to writing JOIN FETCH manually in JPQL — achieves the same eager-loading-in-one-query result declaratively.
2. @Modifying is required only alongside custom @Query UPDATE/DELETE statements — not needed when using setter + save(), since save() is already a built-in, properly handled method.
3. @Param - Method parameters bound via @Param supply the actual runtime value substituted into a named placeholder (:paramName) inside the JPQL WHERE clause.
4. orphanRemoval = true - To remove the child once it becomes orphan, we use orphan removal property of Mapping annotation
