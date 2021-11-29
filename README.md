# job4j_url_shortcut
[![Build Status](https://app.travis-ci.com/hasover/job4j_url_shortcut.svg?branch=master)](https://app.travis-ci.com/hasover/job4j_url_shortcut)

## Описание
Сервис работает через REST API. Чтобы обеспечить безопасность пользователей, все ссылки на сайте заменяются ссылками на наш сервис.

## Функционал

### 1. Регистрация сайта.
Чтобы зарегистрировать сайт в систему нужно отправить запрос POST /registration c телом JSON объекта {site : "job4j.ru"}.
Ответ от сервера : {registration : true/false, login: УНИКАЛЬНЫЙ_КОД, password : УНИКАЛЬНЫЙ_КОД}

### 2. Авторизация.
Пользователь отправляет POST запрос с login и password и получает ключ.
Этот ключ отправляется в запросе в блоке HEAD.
Authorization: Bearer e25d31c5-db66-4cf2-85d4-8faa8c544ad6

### 3. Регистрация URL.
Поле того, как пользователь зарегистрировал свой сайт он может отправлять на сайт ссылки и получать преобразованные ссылки.
POST /convert c телом JSON объекта {url: "https://job4j.ru"}.
Ответ от сервера : {code: УНИКАЛЬНЫЙ_КОД}

### 4. Переадресация. Выполняется без авторизации. 
GET /redirect/УНИКАЛЬНЫЙ_КОД

### 5. Статистика.
По сайту можно получить статистку всех адресов и количество вызовов определенного адреса.
GET /statistic  ответ: {url : URL, total : 0}
GET /statistic/b ответ: {url : "https://job4j.ru", total : 0}









