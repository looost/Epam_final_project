# Final Project - Shows

Основные технологии, используемые в проекте:

  - Java 8
  - Servlet API
  - Tomcat 8
  - MySQL
  - JDBC
  - JSP
  - JSTL
  - Maven
  - Log4j
  - TestNG

## О проекте
Web приложение Shows предназначено для ознакомления с перечнем размещенных в нем сериалов, отслеживания их, а так же возможность оставлять комментарии.
В приложении реализовано разграничение прав доступа по ролям. Всего в нем доступно три роли:
- администратор
- модератор
- пользователь

Для незарегистрированных пользователей обеспечивается доступ к следующему функционалу:
- возможность ознакомления с перечнем сериалов, а также комментариев к ним
- возможность пользоваться поиск
- возможность ознакомиться с рейтингом сериалов, на основании мнения пользователей данного приложения

Для пользователя обеспечивается доступ к следующему функционалу: 
 - возможность регистрации и авторизации в приложении
 - возможность отслеживания сериалов в личном кабинете
 - возможность оценки сериала
 - возможность оставить комментарий к сериалу, редактировать и удалить его
 - в личном кабинете обеспечивается возможность смены пароля или аватара
 - а также ко всем возможностям, которые доступны для незарегистрированных пользователей

Для администратора обеспечивается доступ к следующему функционалу:
 - возможность редактирования сериалов, жанров, студий и стран
 - возможность создания пользователя с заданными правами либо изменение прав уже зарегистрированных пользователей
 - а также ко всем возможностям, которые доступны пользователю
 
Для модератора обеспечивается доступ к следующему функционалу:
 - возможность редактирования сериалов, жанров, студий и стран
 - возможность удаление любых комментариев
 - а также ко всем возможностям, которые доступны пользователю

Для входа с правами администратора используйте логин - "admin" и пароль - "admin", для модератора - "moder" и пароль - "moder"

В дальнейшем планируется расширить возможность оценки сериала при помощи бальной системы.