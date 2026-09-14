# Notes
1. For wildcard search use % like "%...%" in a LIKE operation, otherwise it will try to find an exact match.
2. Like vs Containing (Spring Data derived query keyword) — both do substring matching, but Containing wraps the value with %...% internally.
3. When a SELECT is performed on a column backed by a unique constraint, the result is expected to be at most one entity.
4. Unit test cases are independent test cases and their execution order is also not predefined by default
5. Narrower result fits into a broader type (single entity into a list), but not vice versa — hence List return type is safer.
6. JPQL uses entity field names, not database column names ; DBeaver takes a few seconds of delay before reflecting changes.
7. In @Query use positional parameter(?1, ?2) or named parameter(:title, :name) to define placeholders for method argument value
8. Derived query methods cover Read, Count, Exists, and Delete — not Create/Update
9. When a custom query methods is invoked directly , explicitly annotating it with @Transactional is the safe practice — it may occasionally work without it, but this is not guaranteed. 
        Read operations do not require an explicit transaction, and Spring Data JPA's built-in methods (save, deleteById, etc.) are already transactional internally.
10. A side effect of applying @Transactional on a test method is that Spring automatically rolls back the transaction once the test finishes.


# Details
1. BigDecimal.valueOf() - Used to create a BigDecimal instance from a primitive double or long value — 
        commonly needed when comparing or passing monetary/precision values in queries.
2. Optional.ifPresent() - A functional-style method to execute a block of code only if the Optional contains a non-null value.
3. distinct keyword - provides real value only when a query can return multiple rows with potential duplicates — 
        such as when joins are involved, or when projecting a specific column instead of the full entity.
4. List<Entity>, Entity, Optional<Entity> — most common return types; if there's a probability of null in the entity return type, switch to Optional.


# Recap
1. Create a repository interface that extends JpaRepository<C, T> and annotate it with @Repository
2. Use test class and create dedicated methods annotated with @Test for each category
3. Use builder pattern for object creation as per requirement 
4. Perform operation using JPA Reserved methods - findAll
5. Perform operation using Query subject keywords - findByQuantity, existsByTitle, countByPrice, deleteBySku, findTop5ByCreatedAt
6. Perform operation using Query predicate keywords - findByPriceBetween, findByTitleOrPrice, findBySkuContaining, findByQuantityLessThanAndPriceGreaterThan
7. Define JPQL Query methods in repository and use in Test class - findByNameAndRate, findByStocksBetween


# Annotations
1. @Test - marks a method as a test case to be executed by the testing framework (e.g., JUnit).
2. @Query - used to define a custom JPQL or native SQL query on a repository method, instead of relying on derived query naming conventions.
3. @Rollback - Overrides the default test rollback behavior, allowing the transaction to be committed permanently even within a @Transactional test method.