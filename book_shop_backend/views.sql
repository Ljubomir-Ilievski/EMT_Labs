create materialized view number_of_authors_per_country as
    select country.id, country.name, count(*) as number_of_authors
    from author join country on author.country_id = country.id
    group by country.id, country.name;

create materialized view number_of_books_by_author as
    select author.id, author.name, author.surname, count(*) as number_of_books
    from book join author on book.author_id = author.id
    group by author.id, author.name, author.surname;