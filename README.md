# <img width="180" title="SL_banner" src="images/screens/banner__1.jpg">  <br>Проект по автоматизации тестирования<br> компания СберЛизинг <br>

> Проект включает в себя UI-автотесты для комапнии СберЛизинг, с использованием современного стека технологий, интеграцией в CI/CD процессы и подключением отчётности.


## 🔗 Ссылки на проект и инфраструктуру
* [Тестируемый сайт](https://www.sberleasing.ru)
* [Сборка в Jenkins](https://jenkins.autotests.cloud/job/C40_OlegRazumov_SberleasingProject_lesson14/)
* [Отчет в Allure Report](https://jenkins.autotests.cloud/job/C40_OlegRazumov_SberleasingProject_lesson14/allure/)


## 🛠 Технологический стек

<p align="center">  
<a href="https://www.jetbrains.com/idea/"><img src="images/logo/Intelij_IDEA.svg" width="50" height="50"  alt="IDEA"/></a>  
<a href="https://www.java.com/"><img src="images/logo/Java.svg" width="50" height="50"  alt="Java"/></a>  
<a href="https://github.com/"><img src="images/logo/Github.svg" width="50" height="50"  alt="Github"/></a>  
<a href="https://junit.org/junit5/"><img src="images/logo/JUnit5.svg" width="50" height="50"  alt="JUnit 5"/></a>  
<a href="https://gradle.org/"><img src="images/logo/Gradle.svg" width="50" height="50"  alt="Gradle"/></a>  
<a href="https://selenide.org/"><img src="images/logo/Selenide.svg" width="50" height="50"  alt="Selenide"/></a>  
<a href="https://aerokube.com/selenoid/"><img src="images/logo/Selenoid.svg" width="50" height="50"  alt="Selenoid"/></a>  
<a href="https://github.com/allure-framework/allure2"><img src="images/logo/Allure.svg" width="50" height="50"  alt="Allure"/></a> 
<a href="https://qameta.io/"><img src="images/logo/Allure2.svg" width="50" height="50"  alt="Allure TestOps"/></a>   
<a href="https://www.jenkins.io/"><img src="images/logo/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>  
<a href="https://www.atlassian.com/ru/software/jira/"><img src="images/logo/Jira.svg" width="50" height="50"  alt="Jira"/></a>
<a href="https://telegram.org/"><img src="images/logo/Telegram.svg" width="50" height="50"  alt="Telegram"/></a>
</p>

* **Язык**: Java 17
* **Фреймворки**: Selenide, JUnit 5
* **Сборка**: Gradle 8.x
* **Отчетность**: Allure Report
* **Инфраструктура**: Jenkins, Selenoid
* **Уведомления**: Telegram Bot

---

##  <img width="40" style="vertical-align:middle" title="Jenkins" src="images/logo/Jenkins.svg">  Сборка в Jenkins

Для запуска сборки необходимо перейти в раздел <code>Buld with Parametrs</code>, выбрать параметры экрана, браузер и нажать кнопку <code>Build</code>.
<p align="center">
<img title="Jenkins Build" src="images/screens/jenkins_view.jpg">
</p>

---
## <img width="40" style="vertical-align:middle" title="Allure Report" src="images/logo/Allure.svg"> Мониторинг и отчетность

После выполнения сборки, в блоке <code>История сборок</code> напротив номера сборки появятся значки <code>Allure Report</code>, при клике на которые откроется страница с сформированным html-отчетом и тестовой документацией соответственно.

<p align="center">
<img title="Allure-report" src="images/screens/allure-report_1.jpg"><br>
<img title="Allure-suites" src="images/screens/allure-report_suits.jpg"><br>
<img title="Allure-graph" src="images/screens/allure-report_graph.jpg"><br>
</p>

---

## <img width="40" style="vertical-align:middle" title="Telegram" src="images/logo/Telegram.svg"> Уведомления в Telegram
Так же настроена отправка отчётов прохождения тестов, в Telegram-bot.
<p align="center">
  <img src="images/screens/telegram-bot-src.jpg" width="300">
</p>

---

### <img width="40" style="vertical-align:middle" title="Selenoid" src="images/logo/Selenoid.svg"> Видео выполнения тестов
В отчетах Allure для каждого теста прикреплен видео-скриншот прохождения теста.

<p align="center">
  <img src="images/video/selinoid_scr_video.gif" width="800" controls muted>
  </img>
</p>

---



