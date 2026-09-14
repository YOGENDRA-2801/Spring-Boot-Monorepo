# Notes

1. Spring Test framework (@SpringBootTest) doesn't support constructor-based dependency injection in test classes; field-level @Autowired must be used instead.
2. `... new package(p.id, p.name) ...` — full-qualified class name with new keyword is required in JPQL constructor expressions during interface based projection
3. `toString()` is required — when printing a list of projection objects directly via `System.out.println()`, otherwise the default object hash/reference gets printed.
4. Ways of projection — interface-based projection is best for simple field subsets from existing entity fields; class-based DTO (constructor expression) is best for aggregate/calculated queries
5. interface approach — Pros: minimal code, no `@Query` needed, Spring auto-generates the proxy. Cons: cannot express complex calculations/joins purely via naming convention, readonly.
6. class approach — Pros: full query control (joins, aggregations), type-safe, can hold extra logic. Cons: more boilerplate, query and class must stay in sync manually.
7. Initial `I` for interface and `C` for class in file name — a naming convention practice to visually distinguish interface-based vs class-based projections at a glance.
8. A `@Modifying` query's like update return type is typically `int`/`Integer` (no. of rows affected) or `void`, never an entity/DTO, since it doesn't perform a `SELECT`.
9. In the signature of test method inside test class keep the return type as void and skip writing access modifier
10. In interface approach based on the return type of the repository method spring itself generated the query in a way to fetch only required field

# Details

1. Projection — technique of retrieving only specific fields/columns from an entity instead of the full entity object, used to reduce data transfer and improve performance when the full entity isn't needed.
2. Ordinal & String — refers to `EnumType.ORDINAL` (stores enum as integer index, fragile if enum order changes) vs `EnumType.STRING` (stores enum as its name string, safer and more readable) when persisting enums via `@Enumerated`.
3. Projection with DTO as interface — define an interface with getter methods matching the desired fields; Spring Data JPA generates a proxy at runtime that populates those getters from the query result, without writing any implementation.
4. Projection with DTO as class — define a concrete class with a constructor, and use a JPQL constructor expression (`SELECT new package.ClassName(...)`) to explicitly construct instances of that class from query results.
5. `var` in foreach — using `var` as the loop variable type in an enhanced for-loop lets the compiler infer the type automatically, reducing verbosity especially with long generic/projection type names.
6. `COUNT(p)` — an aggregate JPQL function that counts the number of entity instances (rows) matching the query, often used with `GROUP BY` for grouped counts.
7. Cascade — defines how operations (persist, merge, remove, etc.) on a parent entity automatically propagate to its related child entities in a relationship mapping (e.g., `CascadeType.ALL`, `CascadeType.PERSIST`).
8. `"as"` JPQL keyword — used to assign an alias to an entity or expression within a JPQL query for readability and reference (e.g., `SELECT p AS product FROM ProductEntity p`), similar to SQL aliasing.

# Annotations

1. `@CreationTimestamp` — Hibernate annotation that automatically sets the field's value to the current timestamp when the entity is first persisted (insert time only).
2. `@Enumerated(value = EnumType.STRING)` — tells Hibernate to persist an enum field as its string name in the database column, rather than its ordinal integer position.
3. `@SpringBootTest` — loads the full Spring application context for integration testing, allowing beans like repositories/services to be autowired and tested in a realistic environment.
4. `@Param` — binds a method parameter to a named parameter (`:paramName`) used inside a `@Query` (JPQL/native SQL) string.
5. `@Data` — Lombok annotation generating getters, setters, `toString()`, `equals()`, and `hashCode()`; note that it does not generate a no-args or all-args constructor by default (those need `@NoArgsConstructor`/`@AllArgsConstructor` separately) — relevant when combined with `@RequiredArgsConstructor` for final fields or when a no-args constructor is needed for JPA entities.
6. `@Modifying` — marks a `@Query` method as a data-modifying query (UPDATE/DELETE) rather than a SELECT, required alongside `@Transactional` for such operations to execute correctly.

# Recap

1. Changed database from MySQL to PostgresDB, Provide configuration for PostgresDB with dummy date in classpath file
2. Created Patient Entity (id, name, dob, gender, bloodGroup, createdAt) and Patient Repository
3. Enum inside entity>type folder for blood group field
4. Created PatientServiceTest class in test folder with @SpringBootTest with @Test method
5. findAll with for-each in test method to check everything is working fine
6. Create DTO interface having getters (name, dob) and derived method in repository
7. Create DTO class (id & name) and inside repository write JPQL to fetch only required field
8. Create DTO class (bloodGroup & count) and inside repository write aggregate JPQL to fetch using count & group by
9. Inside repository interface write JPQL to update the name (update & set) - modifying query