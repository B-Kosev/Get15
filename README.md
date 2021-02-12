# Get15

Курсов проект по ООП с Java – Get 15

Цел на проекта: Целта на проекта е да се имплементира играта Get 15. Двама играчи, свързани към сървър, се редуват да избират цифрите от 1 до 9 включително и който играч пръв направи сума от 15 с три цифри печели играта. Веднъж избрана цифра, не може да се избира повторно.
Подход: След внимателно проучване на логиката на играта, бе установено, че проектът, който трябва да се реализира прилича в голяма степен на играта TicTacToe. Вместо игрална дъска с 9 празни клетки е използвана следната матрица:
4 9 2

3 5 7

8 1 6

При използването на този подход може да се приложи логиката на играта TicTacToe. Вместо използването на символи Х и О са използвани цветове, за по-интуитивна реализация. Цвят ЗЕЛЕН отговаря на символ Х, а цвят ОРАНЖЕВ отговаря на символ О.

Имплементация: За реализацията на проекта е използвана основната структура на играта TicTacToe, предоставена заедно с условието, както и логиката за намиране на победител или проверка за пълна дъска. Останалата част от проекта е реализирана самостоятелно. Автоматично е генерирана UML диаграма, за да се визуализират връзките между класовете и интерфейсите. Използвани са следните класове и интерфейси:

•	GetFifteenInterface – представлява интерфейсът, чрез който се имплементира сървъра. 
Метод  char connect(Callback client) се използва за свързването на играч към сървъра, като връща съответния символ, с който ще играе играчът.

Метод void chooseNumber(int row,int col, char token) се използва от играча, за да уведоми сървъра на коя позиция е направен ход.

•	GetFifteenImplServer – това е сървърът, който имплементира интерфейса GetFifteenInterface. Състои се от 2 променливи за играч и игрална дъска (3х3 матрица). Имплементира методите от интерфейса и допълнително има методи за проверка дали даден играч печели и дали игралната дъска е пълна.

•	Callback – интерфейс, който декларира методите, които callback конструкцията трябва да изпълни.

Метод void makeChoice(boolean turn) се използва от сървъра, за да уведоми играча, че е негов ход.

Метод void notify(String msg) се използва от сървъра, за да изпрати съобщение до играча.

Метод void markOpponent(int row,int col,char token) се използва от сървъра, за да уведоми играча за направен ход от противника.

•	CallbackImplementation – имплементира методите на интерфейса Callback.

•	GetFifteenClient – реализация на самия играч като обект. Свързва се към сървъра и комуникира с него за ходовете на играча. Свързан е с класа Controller, за да може да изпраща заявки за ъпдейтване на визуалния интерфейс. Има локална игрална дъска, в която отбелязва кои клетки са използвани.

Метод public void initializeRMI() се извиква при стартирането на класа и прави връзката със самия сървър, както и показва началните съобщения във визуалния интерфейс.

Метод public void markOpponent(int row, int col, char token) маркира хода на противника в локалната дъска за играча и във визуалния интерфейс.

Метод public void mark(int row, int column, char token) маркира хода на играча в локалната дъска, визуалния интерфейс и се обажда на сървъра, за да го уведоми за текущия ход.

•	Controller – контролерът на визуалния интерфейс.
 
Визуален интерфейс: Използвана е показаната по-горе матрица. В горната част на прозореца се уведомява играчът както писмено, така и визуално с кой цвят играе. Също в горната част на прозореца има бутон, който отваря правилата на играта. В средната част на прозореца е самото игрално поле, като ходовете на играчите се маркират със съответния цвят. В долната част на прозореца се показват съобщения, изпращани от сървъра.

Тестване: Кодът е тестван от двама човека, като са покрити максимално много потребителски случаи, в които програмата може да се държи неправилно. Отстранени са всички грешки и бъгове, които са били открити.

Съществуващи проблеми: 

•	Ако един от двамата играчи напусне играта, сървъра и другия играч по никакъв начин не биват уведомявани.

•	При приключване на игра се налага рестартиране на сървъра, за да се започне нова игра.

