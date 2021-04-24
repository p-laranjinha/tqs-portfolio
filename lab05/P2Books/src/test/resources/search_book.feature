Feature: Book search
  To allow a customer to find his favourite books quickly, the library must offer multiple ways to search for a book.

  Background: A sample library
    Given a book with the title 'Book 1', written by 'Author 1', published in 2001-01-01
    And another book with the title 'Book 2', written by 'Author 2', published in 2002-02-02
    And another book with the title 'Book 3', written by 'Author 1', published in 2003-03-03
    And another book with the title 'Book 4', written by 'Author 3', published in 2004-04-04

  Scenario: Search books by author
    When the customer searches for books by the author '1'
    Then 2 books should have been found
    And Book 1 should have the title 'Book 1'
    And Book 2 should have the title 'Book 3'

  Scenario: Search books by publication year
    When the customer searches for books published between 2002-01-01 and 2003-12-01
    Then 2 books should have been found
    And Book 1 should have the title 'Book 3'
    And Book 2 should have the title 'Book 2'

  Scenario: Search books by title
    When the customer searches for books by the title 'Book'
    Then 4 books should have been found
    And Book 1 should have the title 'Book 1'
    And Book 2 should have the title 'Book 2'
    And Book 3 should have the title 'Book 3'
    And Book 4 should have the title 'Book 4'