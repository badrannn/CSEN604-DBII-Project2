Resources

Introduction to SQL Queries performance measurement in PostgreSQL
	https://www.citusdata.com/blog/2018/03/06/postgres-planner-and-its-usage-of-statistics/
	https://wiki.postgresql.org/wiki/Tuning_Your_PostgreSQL_Server
	
	
Advanced Tuning:
	https://www.postgresql.org/docs/13/static/runtime-config.html


Create Statistics:
	https://www.postgresql.org/docs/13/sql-createstatistics.html

How to use EXPLAIN command and understand its output & other stuff:
	https://www.postgresql.org/docs/13/performance-tips.html
	http://www.postgresql.org/docs/13/static/sql-explain.html

Creating an Index for a table: 
	http://www.postgresql.org/docs/13/static/sql-createindex.html

Statistics collected by PostgreSQL:
	https://www.postgresql.org/docs/13/static/planner-stats.html

Query Planner Configuration hint in PostgreSQL:
-	The SET command can be used to change the behavior of the query planner. 
	For example, “SETenable_nestloop=false;” command in postgresql disables the 
	query planner’s use of nestedloopjoin plans.
