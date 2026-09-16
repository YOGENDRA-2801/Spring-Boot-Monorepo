# Notes
1. Reading: CurrentClass - ManyToOne - RelationClass — a way to read relationship annotations
2. Inverse side is only for convenience/navigation — it doesn't impact on database schema (no extra column/table is created).
3. Owning side has actual insert/update control over join table. Hibernate only considers & persist owning side changes in table
4. || - partial participation ; o - full participation ; o can't exist without || but || can exist without o ; typically represented as unidirectional in ER diagrams (min-max notation used in database design).
5. When you add a one-to-one mapping, Hibernate will automatically add a unique constraint on your behalf (on the foreign key column), ensuring true one-to-one cardinality at the database level.
6. @JoinColumn can be applied only at the owning side; the owning side's mapping column is the join column (foreign key column).
7. For a unique column, an index is automatically created by Hibernate (since unique constraints are typically backed by an index for fast lookup).
8. Without mappedBy attribute hibernate will treat both sides as owning sides & try to create a join column (or duplicate join table) on both — mappedBy is required on the inverse side of a bidirectional relationship if present.
9. For ManyToMany mapping, a join table is mandatory, which will be created by Hibernate (either implicitly with a default name, or explicitly via @JoinTable).
10. When a Collection type field is declared, at that point only using the new keyword to create their instance rectifies a NullPointerException — since an uninitialized collection field defaults to null, and attempting operations like .add() on it without initialization throws NPE.
11. OneToOne - The entity that can't exist without other entity has FK  & is owning side
12. ManyToOne / OneToMany - FK / owning side is always at the many side of the table
13. ManyToMany - Decided by us; suggestion - side that update/operates over relation more frequently , side that controls other = has the FK / owns the relation

# Details
1. Cascade — defines how operations (persist, merge, remove, etc.) on a parent entity automatically propagate to its related child entities in a relationship mapping.
2. Owning Side — the side of a relationship that controls/owns the foreign key (or @JoinTable in ManyToMany) and whose changes actually get persisted to the database.
3. Inverse Side — the side of a relationship marked with mappedBy, used purely for navigation convenience; its changes are not directly persisted to the foreign key/join table.
4. Full Participation — every instance of an entity must participate in the relationship (cannot exist without the associated entity) — represented as mandatory participation in ER modeling.
5. Partial Participation — an entity instance may or may not participate in the relationship (can exist independently without the associated entity) — represented as optional participation in ER modeling.

# Annotation
1. @OneToOne — maps a one-to-one relationship between two entities; the owning side typically gets a unique foreign key column via @JoinColumn.
2. @OneToMany — maps a one-to-many relationship; typically used as the inverse side with mappedBy, since the foreign key usually resides on the "many" side.
3. @ManyToOne — maps a many-to-one relationship; this side naturally owns the relationship since it holds the foreign key column.
4. @ManyToMany — maps a many-to-many relationship; requires a join table, with the owning side explicitly defining it via @JoinTable.
5. @JoinColumn — specifies the foreign key column for @OneToOne/@ManyToOne relationships (or inside @JoinTable for @ManyToMany).
6. @JoinTable — defines the join table used in a @ManyToMany relationship, specifying the table name and the foreign key columns pointing to both related entities.

# Recap
1. Update Patient Entity and define it's mapping as per ER Diagram
2. Create Insurance Entity and define it's mapping as per ER Diagram
3. Create Appointment Entity and define it's mapping as per ER Diagram
4. Create Department Entity and define it's mapping as per ER Diagram
5. Create Doctor Entity and define it's mapping as per ER Diagram