# Notes
1. The entity that has foreign key (owning side), the operation to assign/map is also done in Service class of that entity only and using that entity's repository only.
2. Only perform changes on the inverse side explicitly when it's updated entity is required in that scope otherwise it will be done automatically at the end of transaction.
3. In Entity toString() is discouraged because - Bidirectional relationships cause infinite recursion/StackOverflowError , Triggers unwanted lazy loading , Performance overhead , Exposes sensitive/unnecessary data
4. Cascade Need - Without cascading we must persist/manage related entities manually , Cascading automatically propagates operations , parent-child lifecycle dependency
5. Caution - cascading REMOVE/ALL on relationships where the child entity might be shared or independently meaningful can cause unintended data loss — cascade should be used only where the child's lifecycle is genuinely dependent on the parent.
6. Transient state error - Happens when a DB operation (save) is performed on a parent entity that references a child object still in transient state (not yet persisted); rectify using cascade approach (CascadeType.PERSIST/ALL) or by saving the child first.
7. Owning/Inverse decides who controls the DB column/table; Parent/Child decides who logically depends on whom and where cascade should be configured. Owning and Parent side can belong to different entity
8. Operations actually executes via the owning side, but cascade rules (which decide if an operation propagates) are placed based on the parent-child business relationship, wherever that cascade annotation is written.
9. Where to configure cascade: on the parent (business) entity's relationship annotation, pointing toward the child — because cascade should model "parent's lifecycle governs child's lifecycle."
10. Where the actual DB operation executes: always through the owning side (since that's where the FK/join table is controlled) — but cascade lets you trigger it indirectly via the parent's save()/delete(), without manually calling the child's/owning side's repository.
11. Generally prefer LAZY for most relationships (especially collections) and fetch what's needed explicitly using JOIN FETCH in JPQL queries or DTO projections, rather than relying on default EAGER behavior which can silently degrade performance as the object graph grows.

# Details
1. Cascade - A configuration on relationship annotations that automatically propagates a persistence operation (persist, merge, remove, detach, refresh) performed on a parent entity to its associated child entities, without requiring separate manual calls for each.
2. var - Allows local variable type inference — the compiler automatically determines the variable's type from the right-hand side expression at compile time, instead of you explicitly writing the type.
3. Transient to Persistent State : repository.save(entity) - standard & sufficient , entityManager.persist(entity) , Cascading from an already-persistent parent , entityManager.merge(detachedEntity)
4. Fetch Type - Determines when a related entity/collection is loaded from the database relative to when the parent entity itself is loaded, in relationship mappings. Categories - LAZY and EAGER
5. FetchType.EAGER — the related entity/collection is loaded immediately, together with the parent entity, in the same query (or an additional query executed right away) — regardless of whether you actually access it.
6. FetchType.LAZY — the related entity/collection is not loaded immediately; instead, a proxy object is created, and the actual data is fetched only when accessed — provided the session/transaction is still open at that point.
7. JPA Default fetch types - @ManyToOne → EAGER, @OneToOne → EAGER, @OneToMany → LAZY, @ManyToMany → LAZY. The "to-one" default to EAGER because they usually fetch just one related row (cheap), while "to-many" default to LAZY because they could fetch a large collection (expensive if loaded unnecessarily).
8. JPA Domain - Owning & Inverse Side : Purely about which side controls the foreign key/join table in the database mapping. Decided by @JoinColumn/@JoinTable presence. This is a structural/mapping concept — it doesn't inherently say anything about business meaning or lifecycle dependency.
9. Data Domain - Parent & Child Side : About conceptual/business dependency — which entity is the "main" entity and which one logically depends on it or belongs to it. This is a domain/business concept, independent of how the FK is mapped in JPA.
10. LazyInitializationException : Occurs when code tries to access a lazily-loaded association (a relationship marked FetchType.LAZY) after the Hibernate session/transaction has already been closed. Common fixes - Access within transaction , use JPQL with JOIN FETCH or use DTO projection

# Recap
1. Create Service & Repository for all entities
2. insert dummy data for doctor also
3. assignInsuranceToPatient from InsuranceService
4. deletePatient from Insurance Service
5. createNewAppointment from AppointmentService
6. Update the Cascade property of entity class accordingly
7. Test the functionality in the test class

# Annotations
1. @Transactional
2. @ToString.exclude() - Lombok annotation used alongside @ToString (or @Data) to exclude a specific field from the auto-generated toString() method. Commonly used on relationship fields in entities to prevent infinite recursion or unwanted lazy-loading triggers.
3. @JsonIgnore - Jackson annotation that excludes a field from being included in the JSON response/request when an entity is serialized/deserialized via REST controllers. Commonly used to prevent infinite recursion in relationships or to hide sensitive fields from API output.
4. @RequiredArgsConstructor


id field ka non-null hona Hibernate ke liye signal hai ki entity "detached/existing" hai — isliye jab bhi tum ek naya record insert karna chahte ho (whether directly ya cascade se), id ko chhod do (null rehne do), sirf update/fetch scenarios mein hi id explicitly use karo.