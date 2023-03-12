# Приложение Магазин игрушек (Java)

## Информация о проекте
Необходимо написать программу – розыгрыша игрушек в магазине детских товаров.  
Стараемся применять ООП и работу с файлами.

## Желательный функционал программы
- В программе должен быть минимум один класс со следующими свойствами:
    - id игрушки;
    - текстовое название;
    - количество;
    - частота выпадения игрушки (вес в % от 100).
- Метод добавление новых игрушек и возможность изменения веса (частоты выпадения игрушки)
- Возможность организовать розыгрыш игрушек.  

Реализовать консольное приложение заметки, с сохранением, чтением, добавлением, редактированием и удалением заметок.  

## Решение
Для решения данной задачи были применены принципы ООП.  
Приложение построено согласно паттерну MVP.  
Меню реализовано с помощью паттерна Command.  
В качестве хранилища для данных используется TXT файл (при этом можно заменить хранилище на другое без модификации основного кода приложения).
Результат выполнения также логируется в TXT файл (можно подключать сторонний логгер).
```
ID: 3, Name: phone, Chance: 50
ID: 1, Name: teddybear, Chance: 20
ID: 3, Name: phone, Chance: 50
ID: 2, Name: car, Chance: 30
ID: 1, Name: teddybear, Chance: 20
ID: 2, Name: car, Chance: 30
ID: 3, Name: phone, Chance: 50
ID: 1, Name: teddybear, Chance: 20
ID: 3, Name: phone, Chance: 50
ID: 3, Name: phone, Chance: 50
ID: 2, Name: car, Chance: 30
ID: 2, Name: car, Chance: 30
ID: 2, Name: car, Chance: 30
ID: 3, Name: phone, Chance: 50
ID: 1, Name: teddybear, Chance: 20
ID: 3, Name: phone, Chance: 50
ID: 2, Name: car, Chance: 30
ID: 1, Name: teddybear, Chance: 20
ID: 3, Name: phone, Chance: 50
ID: 3, Name: phone, Chance: 50
ID: 2, Name: car, Chance: 30
ID: 3, Name: phone, Chance: 50
ID: 1, Name: teddybear, Chance: 20
ID: 2, Name: car, Chance: 30
ID: 2, Name: car, Chance: 30
ID: 1, Name: teddybear, Chance: 20
ID: 2, Name: car, Chance: 30
ID: 1, Name: teddybear, Chance: 20
ID: 1, Name: teddybear, Chance: 20
ID: 1, Name: teddybear, Chance: 20
```