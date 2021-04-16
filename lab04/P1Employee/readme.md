a) 
EmployeeRepositoryTest - whenFindAlexByName_thenReturnAlexEmployee():
    assertThat( found ).isEqualTo(alex);
EmployeeRepositoryTest - whenInvalidEmployeeName_thenReturnNull():
    assertThat(fromDb).isNull();
EmployeeRestControllerIT - whenValidInput_thenCreateEmployee():
    assertThat(found).extracting(Employee::getName).containsOnly("bob");

b)
A mock of the behaviour of the repository can be found in the test "EmployeeService_UnitTest.java" in the setUp function, so when every test queries the repository it is answered by the mock instead of the database. 

c)
The first difference is that @Mock is from Mockito and @MockBean is from SpringBoot. As such, @Mock is used for unit testing and @MockBean for integration testing that needs a SpringBoot bean to be mocked.

d)
The files "application.properties" and "application-integrationtest.properties" is what SpringBoot uses to store either global variables or the properties needed to run the application, the first file for the application itself, the second for the tests. In this case the tests file has stored the properties needed to run a database for the tests and will be used when the tests want to access a real database instead of mock or one created just for the tests.
