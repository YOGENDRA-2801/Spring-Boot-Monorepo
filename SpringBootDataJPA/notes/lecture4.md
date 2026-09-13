# Note
1. the multiple arguments that are provided in the sort function are not fallback params like if one not provided then other - 
        they are for the same order clash scenario
2. @RequestParam are preferred for the Rest API development
3. Select the Sort Class from org.springframework.data.domain package
4. Page index starts with 0, it's recommended to hardcode the page-size at the backend
5. You cannot pass Pageable and Sort in same method parameter simultaneously separately because 
        Pageable interface itself contains sorting information (it has getSort() method for that)
6. OrderBy approach is less flexible because we have to mention the field name in method signature for different options
7. Sort can be used in both - standard built-in methods & custom derived query methods
8. findAll is a predefined JPA reserved keyword hence can't be used in custom repository method
9. Spring is flexible hence in derived/custom query method when you pass Pageable parameter & keep return type as List<T> 
        then it will only apply pagination (limit/offset) and will not run extra COUNT query that return Page<T> 


# Detail
1. Page().getContent() - Page<T> contains metadata and content hence to get only content we use Page().getContent()
2. @EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO) - Ensures that Page<T> responses are serialized to JSON 
        via a stable DTO (PagedModel) instead of directly serializing the internal PageImpl class. 
        Without this annotation, returning Page<T> directly from a controller triggers a warning.
3. @RequestParam(defaultValue = "xxx") - If client didn't provide the given parameter then spring will use this default value it makes parameter optional without writing required=false
4. findByXxx() - Xxx can be skipped in that case it will behave just like findAll() will return all data without WHERE clause
5. Sort - Default sorting order is ascending unless explicitly descending is not mentioned
6. Bandwidth - Capacity to transfer data from network. Without pagination/other-approach you may bombard client with lots of data at once and cost more bandwidth than required


# Recap
1. Create a Controller for Product
2. Perform sorting via OrderBy approach
   OrderByXxxAsc
   OrderByXxxDesc
3. Perform sorting via Sort Class
   Sort.by(Sort.Direction.ASC, sortField);
   Sort.by(Sort.Order.asc("name"), Sort.Order.desc("salary"));
   Sort.by("lastName").ascending();
4. Perform pagination via Page , Pageable and PageRequest
   findAll(Pageable pageable)
   findByXxx(Www Xxx, Pageable pageable)
   PageRequest.of(pageNumber, pageSize)
   PageRequest.of(pageNumber, pageSize, Sort)