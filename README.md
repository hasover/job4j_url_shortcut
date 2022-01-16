# job4j_url_shortcut
[![Build Status](https://app.travis-ci.com/hasover/job4j_url_shortcut.svg?branch=master)](https://app.travis-ci.com/hasover/job4j_url_shortcut)

* [Описание](#описание)
* [Технологии](#технологии)
* [Функционал](#функционал)
* [Контакты](#контакты)

## Описание
Сервис работает через REST API. Чтобы обеспечить безопасность пользователей, все ссылки на сайте заменяются ссылками на наш сервис.

## Технологии
* Spring Boot (Web, Data, Security)
* JWT
* PostgreSQL
* Maven
* Travis CI

## Функционал

### 1. Регистрация сайта.
Чтобы зарегистрировать сайт в систему нужно отправить запрос POST /registration c телом JSON объекта {site : "job4j.ru"}.
Ответ от сервера : {registration : true/false, login: УНИКАЛЬНЫЙ_КОД, password : УНИКАЛЬНЫЙ_КОД}
![alt text](https://github.com/hasover/job4j_url_shortcut/blob/master/images/reg.PNG)

### 2. Авторизация.
Пользователь отправляет POST запрос с login и password и получает ключ.
Этот ключ отправляется в запросе в блоке HEAD.
![alt text](https://github.com/hasover/job4j_url_shortcut/blob/master/images/login.PNG)

### 3. Регистрация URL.
Поле того, как пользователь зарегистрировал свой сайт он может отправлять на сайт ссылки и получать преобразованные ссылки.
POST /convert c телом JSON объекта {url: "https://job4j.ru/blogs.html"}.
Ответ от сервера : {code: УНИКАЛЬНЫЙ_КОД}
![alt text](https://github.com/hasover/job4j_url_shortcut/blob/master/images/convert.PNG)


### 4. Переадресация. Выполняется без авторизации. 
GET /redirect/УНИКАЛЬНЫЙ_КОД
![alt text](https://github.com/hasover/job4j_url_shortcut/blob/master/images/redirect.PNG)

### 5. Статистика.
По сайту можно получить статистку всех адресов и количество вызовов определенного адреса.
GET /statistic  ответ: {url : URL, total : 0}
GET /statistic/{код} ответ: {url : "https://job4j.ru", total : 0}
![alt text](https://github.com/hasover/job4j_url_shortcut/blob/master/images/stats.PNG)

## Сборка приложения
- Для сборки приложения на вашем компьютере должны быть установлены:
    - JDK 14+
    - Maven
    - PostgreSQL
- Укажите настройки для подключения к БД в файле `src/main/resources/application.properties`
- Выполните команду `mvn package`
- Далее `java -jar target/url_shortcut-0.0.1-SNAPSHOT.jar`

Адрес сервера по умолчанию: http://localhost:8080/

## Контакты
telegram: @hasover










