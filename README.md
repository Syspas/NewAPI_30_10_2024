# Jira API Data Fetcher

Проект **Jira API Data Fetcher** позволяет извлекать данные из задач Jira по их ключам и сохранять данные в текстовых и XML файлах.

## Структура проекта

JairAPI
├── src
│   ├── main
│   │   └── java
│   │       └── org
│   │           └── JiraApiClient
│   │               ├── JiraDataFetcher.java
│   │               ├── ConfigLoader.java
│   │               └── Main.java
│   └── main
│       └── resources
│           └── config.properties
└── build.gradle


## Установка и настройка

1. **Клонируйте репозиторий**:
    ```shell
    git clone <URL>
    cd JairAPI
    ```

2. **Добавьте зависимости в `build.gradle`**:
    ```gradle
    dependencies {
        implementation "com.atlassian.jira:jira-rest-java-client-api:5.2.7"
        implementation "com.atlassian.jira:jira-rest-java-client-core:5.2.7"
        implementation "io.atlassian.fugue:fugue:4.7.2"
    }
    ```

3. **Настройте конфигурацию**:
   Создайте файл `config.properties` в `src/main/resources/` и добавьте в него:
    ```properties
    jiraUrl=https://ваш-домен.atlassian.net
    jiraUsername=ваш-email@example.com
    jiraApiToken=ваш_токен_доступа
    ```

## Описание классов

- **`ConfigLoader`**: Загружает конфигурацию из файла `config.properties`.
- **`JiraDataFetcher`**: Извлекает данные задачи из Jira и сохраняет их в файлы.
- **`Main`**: Основной класс для запуска программы. Принимает ключ задачи в качестве аргумента командной строки.

## Использование

Соберите и запустите проект, указав ключ задачи Jira в аргументах командной строки.

### Пример

1. **Сборка проекта**:
    ```shell
    ./gradlew build
    ```

2. **Запуск программы**:
    ```shell
    java -cp build/libs/JairAPI.jar org.JiraApiClient.BasicTemplate.Main TEST-123
    ```

   Здесь `TEST-123` — это ключ задачи Jira, данные которой будут извлечены и сохранены в файлы `TEST-123.txt` и `TEST-123_details.xml`.

## Примечания

- **API Токен**: Для работы с Jira Cloud создайте токен доступа, используя [руководство Atlassian](https://support.atlassian.com/).
- **Формат данных**: Текстовые данные задачи сохраняются в формате `.txt`, а данные в формате XML — в `.xml`.

## Требования

- **Java 8** или выше
- Доступ к Jira API (домен и учетные данные)

## Лицензия

Этот проект распространяется под лицензией MIT.




