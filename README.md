# SpringBootSearchLibrary
Test方法
# GET
http://localhost:8080/books/1


# POST
http://localhost:8080/books
RequestBody
{
"title": "原子習慣：細微改變帶來巨大成就的實證法則",
"author": "詹姆斯‧克利爾",
"imageUrl": "https://im1.book.com.tw/image/getImage?i=https://www.books.com.tw/img/001/082/25/0010822522.jpg",
"price": 260,
"publishedDate": "2019-06-01 00:00:00"
}



# PUT
http://localhost:8080/books/1
RequestBody
{
"title": "Atomic Habits: An Easy & Proven Way to Build Good Habits & Break Bad Ones",
"author": "James Clear",
"imageUrl": "https://im1.book.com.tw/image/getImage?i=https://www.books.com.tw/img/001/082/25/0010822522.jpg",
"price": 1000000,
"publishedDate": "2019-06-01 00:00:00"
}



# DELETE
http://localhost:8080/books/1
