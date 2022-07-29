# Spring Batch sample to process users and titles and merge them
The code was developed from a base that is available at https://github.com/TechPrimers/spring-batch-example-1
## Load user and title CSV to DB and then write the merged output to another csv userTitle.csv
- `http://localhost:8081/load` - Trigger point for Spring Batch
- `http://localhost:8081/h2-console` - H2 Console for querying the in-memory tables.

## H2 Config
- `testdb` - Database.
- `sa` - User
- `password` - Password.
