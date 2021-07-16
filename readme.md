# Умный сервис прогноза погоды

## Логика работы

1. Пользователь вводит город/страну, для которого он хочет подобрать одежду
2. Пользователь величину терпеть некомфортную температуру в градусах
3. Пользователь нажимает кнопку "GET RECOMMENDATIONS"
4. Пользователь получает информацию о погоде в выбранном городе/стране
5. Пользователь получает список рекомендуемой одежды для нахождения в выбранном городе/стране, основываясь на текущей
погоде там и его температуроустойчивости

### Технологии
- `Kotlin` 
- `Spring`
- `Thymeleaf`
- `Postgres`
<del>
- `Redis` - кэш
</del>
- `Materialize` - стили

Данные о погоде и рекомендациях по поводу одежды подставляются html-страницу шаблонизатором и высылаются пользователю:
![Screenshot](/src/main/resources/images/Screenshot_for_md.jpg)

## При использовании Docker для запуска: 
Для создания сети:
> docker network create weather-network

Для запуска БД:
> docker pull postgres
>
> docker run --name weather_db --rm -e POSTGRES_PASSWORD=password -e POSTGRES_USER=user -e POSTGRES_DB=weather_db --net weather-network -d -p 5436:5432 postgres
>
> gradle -Pflyway.user=user -Pflyway.schemas=public -Pflyway.password=password -Pflyway.url=jdbc:postgresql://localhost:5436/weather_db flywayMigrate

<del>
Для активации кэша:
> docker pull redis
>
> docker run --name redis_cache --rm -p 6379:6379 --net weather-network -d redis

</del>

Для запуска приложения:
> gradle clean bootJar bootRun

## При использовании Docker-compose:
Вместо всех предыдущих команд:
>  docker-compose up

Приложение можно увидеть в браузере [по этому адресу](http://localhost:8080/)

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

# Как контрибутить в сервис
## Правило внесения изменений
### Внесение изменений в код
Изменения вносятся при помощи pull-request, после проверки администратором добавляются в продакшн.

После внесения изменений в код необходимо перестроить образ приложения (версионирование согласно стандартной конвенции):

> docker build -t suvlexant/weather_app:VERSION_NUMBER .

далее необходимо обновить образ в удалённом репозитории:

> docker push suvlexant/weather_app:VERSION_NUMBER

### Внесение изменений в БД

В случае внесения изменений в структуру базы данных необходимо обновить конфигурацию flyway, добавив новый файл согласно
стандартной конвенции, потом создать новый докер-образ миграции (версионирование согласно стандартной конвенции):

> docker build -t suvlexant/weather_db_migration:VERSION_NUMBER -f DockerFileMigration .
 
далее необходимо обновить образ в удалённом репозитории:

> docker push suvlexant/weather_db_migration:VERSION_NUMBER

## Чеклист для самопроверки
- [ ] Запустить все тесты
- [ ] Запустить через `gradle clean bootJar bootRun` и через `docker-compose up`
- [ ] Запустить продовскую конфигурацию через `docker-compose -f docker-compose-prod.yml up`

# Как развёртывать сервис на VDS в первый раз
> sudo snap install docker
> 
> curl -O https://raw.githubusercontent.com/SuvLexAnt/weather/master/docker-compose-prod.yml
> 
> sudo docker-compose -f docker-compose-prod.yml up --remove-orphans



