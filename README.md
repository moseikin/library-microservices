Запуск:
1. config
2. discovery
3. zuul
4. auth. 
   Выдает токены и информацию о пользователе (логин и роли).
   
   /token/getToken - получить jwt-токен, который прикрепляется в заголовок Authorization
   
   /token/getUser - проверяет токен на валидность и извлекает из него логин и роли

Токен передается с заголовком Authorization.
При перезапуске сервиса Auth выданные токены становятся невалидными
Доступ к методам сервиса предполагается только с zuul ip. Это обеспечивается методом hasIpAddress(<ip>) в book.SecurityConfig
   
5. остальные нужные

###Демонстрация работы Resilience4j:
1. TimeOutException. В BookService.getAllBooks раскомментировать вызов метода enableTimeOutExceptionExample()