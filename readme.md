# Умный сервис прогноза погоды
### Сложность
Была выбрана сложность **со звёздочкой**, принято решение реализовывать функционал в формате Web-приложения 

### Технологии
В качестве языка программирования `Kotlin`, 
использован фреймворк `Spring`, 
для фронта выбран шаблонизатор `Thymeleaf`,
база данных для рекомендаций - `Postgres`,
в качестве кэша - `Redis`.

Кроме того, были использованы стандартные стили `Materialize`.

Данные о погоде и рекомендациях по поводу одежды подставляются html-страницу шаблонизатором и высылаются пользователю:
![Screenshot](/src/main/resources/images/Screenshot_for_md.jpg)

## При использовании Docker для запуска: 
Для создания сети:
> docker network create weather_network

Для запуска БД:
> docker pull postgres
> docker run --name weather_db -e POSTGRES_PASSWORD=password -e POSTGRES_USER=user -e POSTGRES_DB=weather_db --net weather-network -d -p 5436:5432 postgres
> gradle -Pflyway.user=user -Pflyway.schemas=public -Pflyway.password=password -Pflyway.url=jdbc:postgresql://localhost:5436/weather_db flywayMigrate

Для активации кэша:
> docker pull redis
> docker run --name redis-cache -p 6379:6379 --net weather-network -d redis

Для построения и запуска приложения:
>  docker build -t weather-app:1.0.0 .
>  docker run -p 8080:8080 --net weather-network -e DATABASE_PORT=5432 -e DATABASE_HOST=${PSQL_HOST} --name=weather-app -it weather-app:1.0.0


## При использовании Docker-compose:
Вместо всех предыдущих команд:
>  docker-compose up --remove-orphans -V
>  gradle -Pflyway.user=user -Pflyway.schemas=public -Pflyway.password=password -Pflyway.url=jdbc:postgresql://localhost:5436/weather_db flywayMigrate

Приложение можно увидеть в браузере [по адресу](http://localhost:8085/)

## Описание структуры приложения        
### Схема работы и архитектура приложения
Архитектура представляет собой реализацию стандартного архитектурного паттерна
**Порты и адаптеры** с управляющими портами в виде контроллеров единственного Web-приложения
с логикой на сервере и шаблонизатора и управляемыми портами в виде БД и адаптера над
сервисом стороннего API.

Схема работы следующая:
- Получаем город пользователя
- Запрашиваем информацию через API у брокера данных
- Используем внутреннюю бизнес логику и обращаемся в БД для выдачи пользователю рекомендаций
- Объединяем полученные ответы в DTO, передаём ViewResolver-у и отправляем HTML-страничку со стилями 
пользователю

