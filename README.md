# cardpay.convert
Implements for cardpay Java Challenger

1. Solution should be as simple as possible. Taking into account the application could be  maintained by other less experienced developers.
*Spring, Java core only, I can't control number of thread in parallel processing*
*The test code is not cover pararrel processing, I do not have time for this*
2. The application must be implemented using the Spring framework.
*it is maven spring boot project, public repository in github*
3. The source code of the application should be formatted as a maven project and posted on GitHub.
4. It is allowed to use dependencies only from public repositories. 
5. Building the final application should be done with the command:
```mvn clean install```
```java -jar target/convert-1.0.0.jar order1.csv order2.csv order3.json```
6. The application must be console-based.
Example of a run command:
``` java -jar orders_parser.jar orders1.csv orders2.json … ordersN.json ```
consider orders1.csv, orders2.json and ordersN.json are files to parse.
7. The result of the execution should be output to stdout stream.
Note: only output data should go to stdout, no logs should be there.
8. Parsing and converting should be done in parallel in multiple threads.
9. It is necessary to provide correct error handling in the source files.
*I do not have time to correct this, since there are difference formats, but it should be same correct error. If I have more time I can do*
For example, instead of a number, the file may have a string value in the amount field.
10. It is allowed to use language tools no higher than Java 8.
11. Consider the possibility of adding new input data formats. For example: XLSX
*I added a interface for other data format*
