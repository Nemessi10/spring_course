# Spring_Course
## Веб-додаток для генерації меню страв
### Functional Requirements:
1. Створення облікового запису;
2. Авторизація користувача;
3. Генерація меню на вказаний проміжок часу (1 день - 1 місяць);
4. Можливість видалити одну чи декілька страв з меню;
5. Заміна однієї чи декількох обраних страв з меню на випадкові інші страви;
6. Сортування страв за категоріями: сніданок, обід, вечеря;
7. Можливість вказати продукт, якого не буде в жодній згенерованій страві + можливість запам'ятати цей вибір для майбутніх генерацій;
8. Можливість зберегти одну чи декілька страв до розділу "Улюблені";
9. Можливість зберегти ціле меню до розділу "Улюблені";
10. Меню має складатися з трьох страв: сніданок, обід, вечерея;
11. Кожна страва має містити опис до якого входять: назва страви, зображення, інградієнти, (по можливості рецепт і покрокова інструкція для приготування);

### System behavior:
1. **Створення облікового запису:**
   - Користувач повинен мати можливість створювати новий обліковий запис, вводячи необхідну інформацію (ім'я, електронна пошта, пароль тощо).
2. **Авторизація користувача:**
   - Після створення облікового запису користувач повинен мати можливість входу в систему, вводячи свої дані для авторизації (електронна пошта і пароль).
3. **Генерація меню на вказаний проміжок часу (1 день - 1 місяць):**
   - Система повинна забезпечити генерацію меню на вказаний період часу, враховуючи можливі обмеження та вимоги користувача.
4. **Видалення страв з меню:**
   - Користувач повинен мати можливість видаляти одну чи декілька страв зі згенерованого меню.
5. **Заміна обраних страв на випадкові інші:**
   - Користувач повинен мати можливість замінити обрані страви на випадкові інші, можливо з урахуванням обмежень чи побажань.
6. **Сортування страв за категоріями:**
   - Меню повинно бути розділене за категоріями (сніданок, обід, вечеря), щоб користувач міг швидко знаходити страви за потрібною категорією.
7. **Вказання продуктів, яких не повинно бути в стравах + запам'ятовування вибору:**
   - Користувач повинен мати можливість вказувати продукти, які не повинні входити в склад згенерованих страв. Система повинна запам'ятовувати цей вибір для майбутніх генерацій якщо так вказав користувач.
8. **Збереження страв та меню в розділ "Улюблені":**
   - Користувач повинен мати можливість зберігати окремі страви та цілі меню в розділ "Улюблені" для подальшого швидкого доступу.
9. **Складання меню з трьох страв:**
   - Згенероване меню повинно включати три страви для кожного прийому їжі: сніданок, обід та вечеря.
10. **Інформація про страви:**
    - Кожна страва має містити певну інформацію: назва, зображення, інгредієнти, а також можливо рецепт і покрокову інструкцію для приготування.
