# web

Содержит основное веб-приложение.

## Запуск приложения

### Сетевые порты

| Номер | Описание             |
|-------|----------------------|
| 8080  | Порт основного API.  |
| 9090  | Порт служебного API. |

### Существующие переменные окружения

| Имя                          | Описание                                                                | Значение по умолчанию |
|------------------------------|-------------------------------------------------------------------------|-----------------------|
| CONFIG_SERVER_URL            | Ссылка на сервер конфигураций.                                          |                       |
| KC_PUBLIC_ISSUER_URI         | Ссылка на Keycloak public область.                                      |                       |
| KC_PUBLIC_USERNAME_ATTRIBUTE | Имя атрибута Keycloak для идентификации пользователей в public области. | sub                   |
| KC_PUBLIC_CLIENT_ID          | Идентификатор клиента Keycloak в public области.                        |                       |
| KC_PUBLIC_CLIENT_SECRET      | Секрет клиента Keycloak в public области.                               |                       |
| KC_SYSTEM_ISSUER_URI         | Ссылка на Keycloak system область.                                      |                       |
| KC_SYSTEM_USERNAME_ATTRIBUTE | Имя атрибута Keycloak для идентификации пользователей в system области. | sub                   |
| KC_SYSTEM_CLIENT_ID          | Идентификатор клиента Keycloak в system области.                        |                       |
| KC_SYSTEM_CLIENT_SECRET      | Секрет клиента Keycloak в system области.                               |                       |
