package me.lodthe.bdaytracker.telegram

enum class MessageLabel(val label: String) {
    START("""
        Привет! Я умею напоминать про Дни Рождения (ДР) Ваших друзей. 
        
        Вы можете добавить какого-то друга вручную (`${ButtonLabel.ADD_FRIEND.label}`) или добавить даты Дней Рождения своих друзей из ВКонтакте (`${ButtonLabel.IMPORT_FROM_VK.label}`).
        
        Когда наступит чей-то День Рождения, я напомню Вам об этом, если вдруг Вы забудете (и никто об этом не узнает)!
        
        Чтобы импортировать друзей из VK, мне потребуется ID Вашего профиля. Также, Ваш профиль должен быть открытым, когда Вы захочешь импортировать друзей (потом можно сделать его приватным).
        
        Обратим внимание, что у некоторых Ваших друзей из VK скрыта дата рождения. Советую просмотреть весь список друзей в боте, в самом начала будут друзья, у которых скрыта дата рождения.
        
        *Также, если Вы используете бота в групповом чате, чтобы написать ему что-то (например, новый VK ID), надо ответить на его сообщение.*
        
    """.trimIndent()),

    HELP(START.label),

    WRONG_COMMAND("""
        Кажется, Вы отправили мне что-то не то.
    """.trimIndent()),

    MENU("""
        `${ButtonLabel.ADD_FRIEND.label}` — добавить вручную нового друга.
        
        `${ButtonLabel.IMPORT_FROM_VK.label}` — обновить даты Дней Рождения друзей из ВКонтакте
        
        `${ButtonLabel.UPDATE_VK_ID.label}` — обновить VK ID профиля, с которого будут импортироваться друзья
        
        `${ButtonLabel.LIST_OF_FRIENDS.label}` — просмотреть список уже добавленных друзей
    """.trimIndent()),

    UPDATE_VK_ID("""
        Отправьте ссылку на профиль ВКонтакте. Например, ```vk.com/durov```
        
        Не забывайте, чтобы сделать это в групповом чате, требуется ответить на сообщение бота.
    """.trimIndent()),

    CANNOT_PARSE_FRIENDS("""
        Не получилось получить Ваших друзей. Проверьте, что профиль не приватный, а также, что ссылка соответствует Вашему профилю.
        
        В ссылке в конце не должно быть символа / (слеш).
        
        Попробуйте еще раз.
    """.trimIndent()),

    IMPORT_FROM_VK("""
        Список друзей из VK был успешно обновлен.
    """.trimIndent()),

    ID_HAS_CHANGED("""
        VK ID и список друзей из VK успешно обновлены.
        
        Новый VK ID: `%d`
    """.trimIndent()),

    ADD_FRIEND("""
        Чтобы добавить информацию о новом друге, отправьте сообщение следующего вида:
        
        `Имя друга`
        `Дата рождения в формате ДД.ММ`
        
        Вторая строка должна содержать день и месяц рождения, сначала день, потом — месяц.
        
        Не забывайте, чтобы сделать это в групповом чате, требуется ответить на сообщение бота.
    """.trimIndent()),

    CANCEL_ADDING_FRIEND("""
        Вы отменили добавление друга.
    """.trimIndent()),

    FRIEND_NAME("""
        Отправьте имя друга, под которым Вы хотите видеть его в списке друзей.
    """.trimIndent()),

    WRONG_FRIEND_NAME_FORMAT("""
        Длина имени не должна превышать %s символов, а также должно состоять из одной строки.
    """.trimIndent()),

    FRIEND_BIRTHDATE("""
        Отправьте дату рождения %s в следующем формате:
        
        ```17.10```
        
        Сначала день, потом — месяц (примеру выше соответствует 17 октября). Вместо точки можно использовать другие символы (например, пробел, запятая, etc).
    """.trimIndent()),

    WRONG_DATE_FORMAT("""
        В дате сначала должен идти день, а за ним — месяц (они должны быть разделены точкой/пробелом). День должен принадлежать диапазону \[1; 31], месяц — \[1; 12]. 
        
        Попробуйте еще раз.
    """.trimIndent()),

    ADD_FRIEND_SUCCESS("""
        Новый друг успешно добавлен!
    """.trimIndent()),

    REMOVE_FRIEND("""
        Чтобы удалить друга, отправьте мне его номер из списка друзей (идет перед фамилией), без точки.
        
        Если этот друг был добавлен из VK, вернуть его можно нажав `${ButtonLabel.IMPORT_FROM_VK.label}`
    """.trimIndent()),

    REMOVE_FRIEND_WRONG_FORMAT("""
        Чтобы удалить друга, отправьте мне его номер из списка (просмотреть список можно нажав `${ButtonLabel.LIST_OF_FRIENDS.label}`). 
        
        Кажется, друга с таким номером нет. Попробовать еще раз можно нажав `${ButtonLabel.REMOVE_FRIEND.label}`.
    """.trimIndent()),

    REMOVE_FRIEND_SUCCESS("""
        %s успешно удален(а) из списка друзей.
    """.trimIndent()),

    FRIENDS_TO_CONGRATULATE_LIST("""
        Не забудь сегодня поздравить друзей с Днем Рождения:
        
        %s
    """.trimIndent()),

    LIST_OF_FRIENDS("")
}

enum class ButtonLabel(val label: String) {
    HELP("\uD83D\uDD39 Помощь"),
    ADD_FRIEND("\uD83D\uDC9A Добавить"),
    CANCEL_ADDING_FRIEND("Отменить добавление друга"),
    IMPORT_FROM_VK("\uD83D\uDD04 Обновить из VK"),
    UPDATE_VK_ID("✏️ Обновить VK ID"),
    LIST_OF_FRIENDS("\uD83D\uDC68\uD83C\uDFFC Список друзей"),
    LIST_OF_FRIENDS_PREVIOUS_PAGE("⬅️ Назад"),
    LIST_OF_FRIENDS_NEXT_PAGE("➡️ Вперед"),
    MENU("\uD83D\uDCDD Меню"),
    GET_ID("❓ Узнать VK ID"),
    REMOVE_FRIEND("❌ Удалить друга")
}

enum class TextLabel(val label: String) {
    EMPTY_FRIEND_LIST("""
        К сожалению, пока что Ваш список друзей пуст. Вы можете добавить друга вручную или импортировать друзей из VK.
    """.trimIndent()),

    NO_BIRTHDATE_DATA("""
        Нет информации
    """.trimIndent())
}