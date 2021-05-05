package com.example.navtelsmartbt.ntcb_java.telemetry;

//класс описаний всех полей протокола FLEX (до 3.0)
public class DescriptorsList {

        static private int index = 0; //чисто для добавления

        //параметры, считываемые по протоколу FLEX 1.0
        static private ParameterDescriptor[] flex10List = new ParameterDescriptor[]{
                new ParameterDescriptor(++index, "index", 4), //Сквозной номер записи в энергонезависимой памяти
                new ParameterDescriptor(++index, "event_code", 2),//Код события, соответствующий данной записи
                new ParameterDescriptor(++index, "event_time", 4),// Время события
                new ParameterDescriptor(++index, "status", 1),// Статус устройства
                new ParameterDescriptor(++index, "module1_status", 1),//Статус функциональных модулей 1
                new ParameterDescriptor(++index, "module2_status", 1),//Статус функциональных модулей 2
                new ParameterDescriptor(++index, "gsm_strengh", 1),//Уровень GSM
                new ParameterDescriptor(++index, "gnss_status", 1),//Состояние навигационного датчика GPS/ГЛОНАСС
                new ParameterDescriptor(++index, "prevValidGNSSTime", 4),//Время последних валидных координат (до произошедшего события)
                new ParameterDescriptor(++index, "prevValidLatitude", 4),//Последняя валидная широта
                new ParameterDescriptor(++index, "prevValidLongitude", 4),//Последняя валидная долгота
                new ParameterDescriptor(++index, "prevValidAltitude", 4),//Последняя валидная высота
                new ParameterDescriptor(++index, "prevValidSpeed", 4),//Последняя валидная скорость
                new ParameterDescriptor(++index, "prevValidBearing", 2),//Последний валидный курс
                new ParameterDescriptor(++index, "mileage", 4),// Текущий пробег
                new ParameterDescriptor(++index, "prevMileage", 4),//Последний отрезок пути
                new ParameterDescriptor(++index, "seconds", 2),//Общее количество секунд на последнем отрезке пути
                new ParameterDescriptor(++index, "validSeconds", 2),//Количество секунд на последнем отрезке пути, по которым вычислялся пробег (валидная навигация)
                new ParameterDescriptor(++index, "mainVoltage", 2),//Напряжение на основном источнике питания, мВ
                new ParameterDescriptor(++index, "backupVoltage", 2),//Напряжение на резервном источнике питания, мВ
                new ParameterDescriptor(++index, "Ain1", 2),//Напряжение на аналоговом входе 1 (Ain1), мВ
                new ParameterDescriptor(++index, "Ain2", 2),//Напряжение на аналоговом входе 2 (Ain2), мВ
                new ParameterDescriptor(++index, "Ain3", 2),//Напряжение на аналоговом входе 3 (Ain3), мВ
                new ParameterDescriptor(++index, "Ain4", 2),//Напряжение на аналоговом входе 4 (Ain4), мВ
                new ParameterDescriptor(++index, "Ain5", 2),//Напряжение на аналоговом входе 5 (Ain5), мВ
                new ParameterDescriptor(++index, "Ain6", 2),//Напряжение на аналоговом входе 6 (Ain6), мВ
                new ParameterDescriptor(++index, "Ain7", 2),//Напряжение на аналоговом входе 7 (Ain7), мВ
                new ParameterDescriptor(++index, "Ain8", 2),//Напряжение на аналоговом входе 8 (Ain8), мВ
                new ParameterDescriptor(++index, "discrete1", 1),//Текущие показания дискретных датчиков 1
                new ParameterDescriptor(++index, "discrete2", 1),//Текущие показания дискретных датчиков 2
                new ParameterDescriptor(++index, "out1", 1),//Текущее состояние выходов 1
                new ParameterDescriptor(++index, "out2", 1),//Текущее состояние выходов 2
                new ParameterDescriptor(++index, "counter1", 4),//Показания счетчика импульсов 1
                new ParameterDescriptor(++index, "counter2", 4),//Показания счетчика импульсов 2
                new ParameterDescriptor(++index, "freqAnalogFuelLevel1", 2),//Частота на аналогово-частотном датчике уровня топлива 1
                new ParameterDescriptor(++index, "freqAnalogFuelLevel2", 2),//Частота на аналогово-частотном датчике уровня топлива 2
                new ParameterDescriptor(++index, "engineHours", 4),//Моточасы, подсчитанные во время срабатывания датчика работы генератора
                new ParameterDescriptor(++index, "rs485FuelLevel1", 2),//Уровень топлива, измеренный датчиком уровня топлива 1 RS-485
                new ParameterDescriptor(++index, "rs485FuelLevel2", 2),//Уровень топлива, измеренный датчиком уровня топлива 2 RS-485
                new ParameterDescriptor(++index, "rs485FuelLevel3", 2),//Уровень топлива, измеренный датчиком уровня топлива 3 RS-485
                new ParameterDescriptor(++index, "rs485FuelLevel4", 2),//Уровень топлива, измеренный датчиком уровня топлива 4 RS-485
                new ParameterDescriptor(++index, "rs485FuelLevel5", 2),//Уровень топлива, измеренный датчиком уровня топлива 5 RS-485
                new ParameterDescriptor(++index, "rs485FuelLevel6", 2),//Уровень топлива, измеренный датчиком уровня топлива 6 RS-485
                new ParameterDescriptor(++index, "rs232FuelLevel", 2),//Уровень топлива, измеренный датчиком уровня топлива RS-232
                new ParameterDescriptor(++index, "temperature1", 1),//Температура с цифрового датчика 1 (в градусах Цельсия)
                new ParameterDescriptor(++index, "temperature2", 1),//Температура с цифрового датчика 2 (в градусах Цельсия)
                new ParameterDescriptor(++index, "temperature3", 1),//Температура с цифрового датчика 3 (в градусах Цельсия)
                new ParameterDescriptor(++index, "temperature4", 1),//Температура с цифрового датчика 4 (в градусах Цельсия)
                new ParameterDescriptor(++index, "temperature5", 1),//Температура с цифрового датчика 5 (в градусах Цельсия)
                new ParameterDescriptor(++index, "temperature6", 1),//Температура с цифрового датчика 6 (в градусах Цельсия)
                new ParameterDescriptor(++index, "temperature7", 1),//Температура с цифрового датчика 7 (в градусах Цельсия)
                new ParameterDescriptor(++index, "temperature8", 1),//Температура с цифрового датчика 8 (в градусах Цельсия)
                new ParameterDescriptor(++index, "fuelLevelCAN", 2),//CAN Уровень топлива в баке
                new ParameterDescriptor(++index, "fuelConsumptionCAN", 4),//CAN Полный расход топлива
                new ParameterDescriptor(++index, "rpmCAN", 2),//CAN Обороты двигателя
                new ParameterDescriptor(++index, "coolantTempCAN", 1),//CAN Температура охлаждающей жидкости (двигателя)
                new ParameterDescriptor(++index, "mileageCAN", 4),//CAN Полный пробег ТС
                new ParameterDescriptor(++index, "axeLoadCAN1", 2),//CAN Нагрузка на ось 1
                new ParameterDescriptor(++index, "axeLoadCAN2", 2),//CAN Нагрузка на ось 2
                new ParameterDescriptor(++index, "axeLoadCAN3", 2),//CAN Нагрузка на ось 3
                new ParameterDescriptor(++index, "axeLoadCAN4", 2),//CAN Нагрузка на ось 4
                new ParameterDescriptor(++index, "axeLoadCAN5", 2),//CAN Нагрузка на ось 5
                new ParameterDescriptor(++index, "accelPedalCAN", 1),//CAN Положение педали газа
                new ParameterDescriptor(++index, "brakePedalCAN", 1),//CAN Положение педали тормоза
                new ParameterDescriptor(++index, "engineLoadCAN", 1),//CAN Нагрузка на двигатель
                new ParameterDescriptor(++index, "dieselFilterLiqLvlCAN", 2),//CAN Уровень жидкости в дизельном фильтре выхлопных газов
                new ParameterDescriptor(++index, "engineHoursCAN", 4),//CAN Полное время работы двигателя
                new ParameterDescriptor(++index, "kmToInspectionCAN", 2),//CAN Расстояние до ТО
                new ParameterDescriptor(++index, "speedCAN", 1)//CAN скорость в км/ч
        };

        //параметры, считываемые по протоколу FLEX 2.0
        static private ParameterDescriptor[] flex20List = new ParameterDescriptor[]{
                new ParameterDescriptor(++index, "infoGNSS", 8), //Информация о навигации
                new ParameterDescriptor(++index, "HDOP_PDOP", 2), //HDOP и PDOP штатного приёмника
                new ParameterDescriptor(++index, "addtnlGNSSstate", 1), //Состояние дополнительного высокоточного навигационного приёмника
                new ParameterDescriptor(++index, "addtnlGNSSlatLon", 16), //Широта и долгота координаты от высокоточного приёмника
                new ParameterDescriptor(++index, "addtnlGNSSalt", 4), //высота от высокоточного приёмника
                new ParameterDescriptor(++index, "addtnlGNSSbearing", 2), //курс от высокоточного приёмника
                new ParameterDescriptor(++index, "addtnlGNSSspeed", 4), //скорость от высокоточного приёмника
                new ParameterDescriptor(++index, "infoLBS", 37), //Информация о текущей базовой станции (LBS) и соседних станциях
                new ParameterDescriptor(++index, "fuelRS485Temp1", 1),//Температура, измеренная датчиком уровня топлива 1 RS485
                new ParameterDescriptor(++index, "fuelRS485Temp2", 1),//Температура, измеренная датчиком уровня топлива 2 RS485
                new ParameterDescriptor(++index, "fuelRS485Temp3", 1),//Температура, измеренная датчиком уровня топлива 3 RS485
                new ParameterDescriptor(++index, "fuelRS485Temp4", 1),//Температура, измеренная датчиком уровня топлива 4 RS485
                new ParameterDescriptor(++index, "fuelRS485Temp5", 1),//Температура, измеренная датчиком уровня топлива 5 RS485
                new ParameterDescriptor(++index, "fuelRS485Temp6", 1),//Температура, измеренная датчиком уровня топлива 6 RS485
                new ParameterDescriptor(++index, "fuelRS484LevelTemp7", 3),//Уровень и температура топлива, измеренная датчиком 7 RS485
                new ParameterDescriptor(++index, "fuelRS484LevelTemp8", 3),//Уровень и температура топлива, измеренная датчиком 8 RS485
                new ParameterDescriptor(++index, "fuelRS484LevelTemp9", 3),//Уровень и температура топлива, измеренная датчиком 9 RS485
                new ParameterDescriptor(++index, "fuelRS484LevelTemp10", 3),//Уровень и температура топлива, измеренная датчиком 10 RS485
                new ParameterDescriptor(++index, "fuelRS484LevelTemp11", 3),//Уровень и температура топлива, измеренная датчиком 11 RS485
                new ParameterDescriptor(++index, "fuelRS484LevelTemp12", 3),//Уровень и температура топлива, измеренная датчиком 12 RS485
                new ParameterDescriptor(++index, "fuelRS484LevelTemp13", 3),//Уровень и температура топлива, измеренная датчиком 13 RS485
                new ParameterDescriptor(++index, "fuelRS484LevelTemp14", 3),//Уровень и температура топлива, измеренная датчиком 14 RS485
                new ParameterDescriptor(++index, "fuelRS484LevelTemp15", 3),//Уровень и температура топлива, измеренная датчиком 15 RS485
                new ParameterDescriptor(++index, "fuelRS484LevelTemp16", 3),//Уровень и температура топлива, измеренная датчиком 16 RS485
                new ParameterDescriptor(++index, "tirePressure1_2", 6),//Информация о давлении в шинах с датчиков 1 и 2
                new ParameterDescriptor(++index, "tirePressure3_to_6", 12),//Информация о давлении в шинах с датчиков 3, 4, 5 и 6
                new ParameterDescriptor(++index, "tirePressure7_to_14", 24),//Информация о давлении в шинах с датчиков с 7 по 14 включительно
                new ParameterDescriptor(++index, "tirePressure15_to_30", 48),//Информация о давлении в шинах с датчиков с 15 по 30 включительно
                new ParameterDescriptor(++index, "tachoDriverCard", 1),//Данные от тахографа: Активность водителей и состояние слотов карт.
                new ParameterDescriptor(++index, "tachoMode", 1),//Режим работы тахографа/ карта
                new ParameterDescriptor(++index, "tachoFlags", 2),//Флаги состояния от тахографа
                new ParameterDescriptor(++index, "tachoSpeed", 1),//Скорость по тахографу
                new ParameterDescriptor(++index, "tachoMileage", 4),//Одометр по тахографу
                new ParameterDescriptor(++index, "tachoTime", 4),//время по тахографу
                new ParameterDescriptor(++index, "driveStatusFromDisplay", 1),//Текущее состояние водителя принятое от дисплейного модуля
                new ParameterDescriptor(++index, "displayMessageIndex", 4),//Индекс последнего полученного/прочитанного сообщения на дисплейном модуле
                new ParameterDescriptor(++index, "displayMessageDT", 2),//Приращение к времени относительно предыдущей записи
                new ParameterDescriptor(++index, "accelerometer", 6),//линейные ускорения по осям
                new ParameterDescriptor(++index, "ecoLimitDuration", 2),//EcoDriving. Длительность превышения порога
                new ParameterDescriptor(++index, "ecoAccerationMax", 6),//EcoDriving. максимальные ускорения, замедления, боковые перегрузки
                new ParameterDescriptor(++index, "passengerTrafficCount1_2", 2),//Данные счетчиков пассажиропотока 1 и 2
                new ParameterDescriptor(++index, "passengerTrafficCount3_4", 2),//Данные счетчиков пассажиропотока 3 и 4
                new ParameterDescriptor(++index, "passengerTrafficCount5_6", 2),//Данные счетчиков пассажиропотока 5 и 6
                new ParameterDescriptor(++index, "passengerTrafficCount7_8", 2),//Данные счетчиков пассажиропотока 7 и 8
                new ParameterDescriptor(++index, "passengerTrafficCount9_10", 2),//Данные счетчиков пассажиропотока 9 и 10
                new ParameterDescriptor(++index, "passengerTrafficCount11_12", 2),//Данные счетчиков пассажиропотока 11 и 12
                new ParameterDescriptor(++index, "passengerTrafficCount13_14", 2),//Данные счетчиков пассажиропотока 13 и 14
                new ParameterDescriptor(++index, "passengerTrafficCount15_16", 2),//Данные счетчиков пассажиропотока 15 и 16
                new ParameterDescriptor(++index, "autoInformStatus", 1),//Статус автоинформатора
                new ParameterDescriptor(++index, "lastGeoID", 2),// ID последней геозоны
                new ParameterDescriptor(++index, "lastStopID", 2),// ID последней остановки
                new ParameterDescriptor(++index, "curRouteID", 2),// ID текущего маршрута
                new ParameterDescriptor(++index, "cameraStatus", 1),// Статус камеры
        };

        //параметры, считываемые по протоколу FLEX 3.0
        static private ParameterDescriptor[] flex30List = new ParameterDescriptor[]{
                new ParameterDescriptor(++index, "deviceStatus2", 1), //Статус устройства 2
                new ParameterDescriptor(++index, "modulesStatus3", 1), // Статус функциональных модулей 3
                new ParameterDescriptor(++index, "communicationStatus", 1), //  Статус состояния связи
                new ParameterDescriptor(++index, "discrete3", 1), //  Текущие показания дискретных датчиков 3
                new ParameterDescriptor(++index, "counter3", 4),//Показания счетчика импульсов 3
                new ParameterDescriptor(++index, "counter4", 4),//Показания счетчика импульсов 4
                new ParameterDescriptor(++index, "counter5", 4),//Показания счетчика импульсов 5
                new ParameterDescriptor(++index, "counter6", 4),//Показания счетчика импульсов 6
                new ParameterDescriptor(++index, "counter7", 4),//Показания счетчика импульсов 7
                new ParameterDescriptor(++index, "counter8", 4),//Показания счетчика импульсов 8
                new ParameterDescriptor(++index, "freqAnalog3", 2),//Частота на аналогово-частотном датчике 3
                new ParameterDescriptor(++index, "freqAnalog4", 2),//Частота на аналогово-частотном датчике 4
                new ParameterDescriptor(++index, "freqAnalog5", 2),//Частота на аналогово-частотном датчике 5
                new ParameterDescriptor(++index, "freqAnalog6", 2),//Частота на аналогово-частотном датчике 6
                new ParameterDescriptor(++index, "freqAnalog7", 2),//Частота на аналогово-частотном датчике 7
                new ParameterDescriptor(++index, "freqAnalog8", 2),//Частота на аналогово-частотном датчике 8
                new ParameterDescriptor(++index, "virtAccelStatus", 1),//Состояние виртуальных датчиков акселерометра
                new ParameterDescriptor(++index, "internalInlination", 1),//Внутренний датчик угла наклона. Угол наклона относительно местной вертикали
                new ParameterDescriptor(++index, "internalPitchRoll", 2),//Внутренний датчик наклона. Углы наклона относительно отвесной линии
                new ParameterDescriptor(++index, "extrnlAngles", 3),//Внешний датчик угла наклона. Отклонения по осям
                new ParameterDescriptor(++index, "ecoAccelMaxVert", 2),//EcoDriving. максимальное вертикальное ускорение
                new ParameterDescriptor(++index, "ecoMaxSpeed", 1),//EcoDriving. Максимальное значение скорости за период
                new ParameterDescriptor(++index, "ecoSpeedLimitStatus", 1),//EcoDriving. Состояние порогов скорости
                new ParameterDescriptor(++index, "ecoAccelLimitStatus", 3),//EcoDriving. Состояние порогов ускорения
                new ParameterDescriptor(++index, "freqRS485FuelLvlSensor1", 2),// Частота на выходе ДУТ 485 №1
                new ParameterDescriptor(++index, "freqRS485FuelLvlSensor2", 2),// Частота на выходе ДУТ 485 №2
                new ParameterDescriptor(++index, "freqRS485FuelLvlSensor3", 2),// Частота на выходе ДУТ 485 №3
                new ParameterDescriptor(++index, "freqRS485FuelLvlSensor4", 2),// Частота на выходе ДУТ 485 №4
                new ParameterDescriptor(++index, "freqRS485FuelLvlSensor5", 2),// Частота на выходе ДУТ 485 №5
                new ParameterDescriptor(++index, "freqRS485FuelLvlSensor6", 2),// Частота на выходе ДУТ 485 №6
                new ParameterDescriptor(++index, "freqRS485FuelLvlSensor7", 2),// Частота на выходе ДУТ 485 №7
                new ParameterDescriptor(++index, "freqRS485FuelLvlSensor8", 2),// Частота на выходе ДУТ 485 №8
                new ParameterDescriptor(++index, "freqRS485FuelLvlSensor9", 2),// Частота на выходе ДУТ 485 №9
                new ParameterDescriptor(++index, "freqRS485FuelLvlSensor10", 2),// Частота на выходе ДУТ 485 №10
                new ParameterDescriptor(++index, "freqRS485FuelLvlSensor11", 2),// Частота на выходе ДУТ 485 №11
                new ParameterDescriptor(++index, "freqRS485FuelLvlSensor12", 2),// Частота на выходе ДУТ 485 №12
                new ParameterDescriptor(++index, "freqRS485FuelLvlSensor13", 2),// Частота на выходе ДУТ 485 №13
                new ParameterDescriptor(++index, "freqRS485FuelLvlSensor14", 2),// Частота на выходе ДУТ 485 №14
                new ParameterDescriptor(++index, "freqRS485FuelLvlSensor15", 2),// Частота на выходе ДУТ 485 №15
                new ParameterDescriptor(++index, "freqRS485FuelLvlSensor16", 2),// Частота на выходе ДУТ 485 №16
                new ParameterDescriptor(++index, "highPrecTemp1", 2),// Высокоточный датчик температуры 1
                new ParameterDescriptor(++index, "highPrecTemp2", 2),// Высокоточный датчик температуры 2
                new ParameterDescriptor(++index, "highPrecTemp3", 2),// Высокоточный датчик температуры 3
                new ParameterDescriptor(++index, "highPrecTemp4", 2),// Высокоточный датчик температуры 4
                new ParameterDescriptor(++index, "highPrecHumidity1", 1),// Высокоточный датчик влажности 1
                new ParameterDescriptor(++index, "highPrecHumidity2", 1),// Высокоточный датчик влажности 2
                new ParameterDescriptor(++index, "highPrecHumidity3", 1),// Высокоточный датчик влажности 3
                new ParameterDescriptor(++index, "highPrecHumidity4", 1),// Высокоточный датчик влажности 4
                new ParameterDescriptor(++index, "fuelLvlSensorStatus", 2),// Датчик расхода топлива. Статус датчика
                new ParameterDescriptor(++index, "fuelLvlSensorFaults", 4),//Датчик расхода топлива. Информация о неисправностях
                new ParameterDescriptor(++index, "fuelLvlSensorConsumptionAll", 4),//Датчик расхода топлива. Суммарный расход топлива
                new ParameterDescriptor(++index, "fuelLvlSensorConsumptionTrip", 4),//Датчик расхода топлива. Суммарный расход топлива за поездку
                new ParameterDescriptor(++index, "fuelLvlSensorFlowSpeed", 2),//Датчик расхода топлива. Скорость потока
                new ParameterDescriptor(++index, "fuelLvlSensorChamberVol", 4),//Датчик расхода топлива. Суммарный объем топлива камеры подачи
                new ParameterDescriptor(++index, "fuelLvlSensorChamberSpeed", 2),//Датчик расхода топлива. Текущая скорость потока камеры подачи
                new ParameterDescriptor(++index, "fuelLvlSensorChamberTemp", 2),//Датчик расхода топлива. Температура камеры подачи
                new ParameterDescriptor(++index, "fuelLvlSensorReturnVol", 4),//Датчик расхода топлива. Суммарный объем топлива камеры обратки
                new ParameterDescriptor(++index, "fuelLvlSensorReturnSpeed", 2),//Датчик расхода топлива.  Текущая скорость потока камеры обратки
                new ParameterDescriptor(++index, "fuelLvlSensorReturnTemp", 2),//Датчик расхода топлива. Температура камеры обратки
                new ParameterDescriptor(++index, "refrigeratorStatus", 2),//Рефрижераторная установка. Состояние установки
                new ParameterDescriptor(++index, "refrigeratorTempSect1", 2),//Рефрижераторная установка. Температура рефрижератора в секции 1 (Температура ХОУ)
                new ParameterDescriptor(++index, "refrigeratorTempSect2", 2),//Рефрижераторная установка. Температура рефрижератора в секции 2
                new ParameterDescriptor(++index, "refrigeratorTempSect3", 2),//Рефрижераторная установка. Температура рефрижератора в секции 3
                new ParameterDescriptor(++index, "refrigeratorTempSet1", 2),//Рефрижераторная установка. Температура установленная 1
                new ParameterDescriptor(++index, "refrigeratorTempSet2", 2),//Рефрижераторная установка. Температура установленная 2
                new ParameterDescriptor(++index, "refrigeratorTempSet3", 2),//Рефрижераторная установка. Температура установленная 3
                new ParameterDescriptor(++index, "refrigeratorAirTemp", 2),//Рефрижераторная установка. Температура окружающего воздуха
                new ParameterDescriptor(++index, "refrigeratorCoolantTemp", 2),//Рефрижераторная установка. Температура охлаждающей жидкости
                new ParameterDescriptor(++index, "refrigeratorAccVolt", 2),//Рефрижераторная установка. Напряжение аккумулятора
                new ParameterDescriptor(++index, "refrigeratorAccAmp", 2),//Рефрижераторная установка. Сила тока аккумулятора
                new ParameterDescriptor(++index, "refrigeratorEngineHours", 4),//Рефрижераторная установка. Моточасы работы от двигателя
                new ParameterDescriptor(++index, "refrigeratorHours", 4),//Рефрижераторная установка. Моточасы работы от сети
                new ParameterDescriptor(++index, "refrigeratorErrors", 4),//Рефрижераторная установка. Количество ошибок и Код самой важной ошибки
                new ParameterDescriptor(++index, "refrigeratorErrors2_3", 4),//Рефрижераторная установка.  Код 2й и 3й по важности ошибки
                new ParameterDescriptor(++index, "refrigeratorErrors4_5_6", 6),//Рефрижераторная установка.  Код 4й, 5й и 6й по важности ошибки
                new ParameterDescriptor(++index, "refrigeratorEngineStatus", 3),//Рефрижераторная установка. Состояние двигателя
                new ParameterDescriptor(++index, "refrigeratorCompressor", 1),//Рефрижераторная установка. Конфигурация компрессора
                new ParameterDescriptor(++index, "geoZoneInfo", 2),//Информация о нахождении в геозонах
                new ParameterDescriptor(++index, "safetyFlagsCAN", 2),//CAN. Флаги состояния безопасности
                new ParameterDescriptor(++index, "safetyEventCAN", 1),//CAN. События состояния безопасности
                new ParameterDescriptor(++index, "faultsCtrlCAN", 4),//CAN. Контроллеры аварии
                new ParameterDescriptor(++index, "faultsInfoCAN", 5),//CAN. Информация о неисправностях
                new ParameterDescriptor(++index, "usersEngineHours1", 4),//Пользовательские моточасы 1 (работа под нагрузкой)
                new ParameterDescriptor(++index, "usersEngineHours2", 4),//Пользовательские моточасы 2 (работа под нагрузкой)
                new ParameterDescriptor(++index, "usersParamByte1", 1),// Пользовательский параметр 1 байт №1
                new ParameterDescriptor(++index, "usersParamByte2", 1),// Пользовательский параметр 1 байт №2
                new ParameterDescriptor(++index, "usersParamByte3", 1),// Пользовательский параметр 1 байт №3
                new ParameterDescriptor(++index, "usersParamByte4", 1),// Пользовательский параметр 1 байт №4
                new ParameterDescriptor(++index, "usersParamByte5", 1),// Пользовательский параметр 1 байт №5
                new ParameterDescriptor(++index, "usersParamByte6", 1),// Пользовательский параметр 1 байт №6
                new ParameterDescriptor(++index, "usersParamByte7", 1),// Пользовательский параметр 1 байт №7
                new ParameterDescriptor(++index, "usersParamByte8", 1),// Пользовательский параметр 1 байт №8
                new ParameterDescriptor(++index, "usersParamByte9", 1),// Пользовательский параметр 1 байт №9
                new ParameterDescriptor(++index, "usersParamByte10", 1),// Пользовательский параметр 1 байт №10
                new ParameterDescriptor(++index, "usersParamByte11", 1),// Пользовательский параметр 1 байт №11
                new ParameterDescriptor(++index, "usersParamByte12", 1),// Пользовательский параметр 1 байт №12
                new ParameterDescriptor(++index, "usersParamByte13", 1),// Пользовательский параметр 1 байт №13
                new ParameterDescriptor(++index, "usersParamByte14", 1),// Пользовательский параметр 1 байт №14
                new ParameterDescriptor(++index, "usersParamByte15", 1),// Пользовательский параметр 1 байт №15
                new ParameterDescriptor(++index, "usersParamByte16", 1),// Пользовательский параметр 1 байт №16
                new ParameterDescriptor(++index, "usersParamWord1", 2),// Пользовательский параметр 2 байта №1
                new ParameterDescriptor(++index, "usersParamWord2", 2),// Пользовательский параметр 2 байта №2
                new ParameterDescriptor(++index, "usersParamWord3", 2),// Пользовательский параметр 2 байта №3
                new ParameterDescriptor(++index, "usersParamWord4", 2),// Пользовательский параметр 2 байта №4
                new ParameterDescriptor(++index, "usersParamWord5", 2),// Пользовательский параметр 2 байта №5
                new ParameterDescriptor(++index, "usersParamWord6", 2),// Пользовательский параметр 2 байта №6
                new ParameterDescriptor(++index, "usersParamWord7", 2),// Пользовательский параметр 2 байта №7
                new ParameterDescriptor(++index, "usersParamWord8", 2),// Пользовательский параметр 2 байта №8
                new ParameterDescriptor(++index, "usersParamWord9", 2),// Пользовательский параметр 2 байта №9
                new ParameterDescriptor(++index, "usersParamWord10", 2),// Пользовательский параметр 2 байта №10
                new ParameterDescriptor(++index, "usersParamWord11", 2),// Пользовательский параметр 2 байта №11
                new ParameterDescriptor(++index, "usersParamWord12", 2),// Пользовательский параметр 2 байта №12
                new ParameterDescriptor(++index, "usersParamWord13", 2),// Пользовательский параметр 2 байта №13
                new ParameterDescriptor(++index, "usersParamWord14", 2),// Пользовательский параметр 2 байта №14
                new ParameterDescriptor(++index, "usersParamWord15", 2),// Пользовательский параметр 2 байта №15
                new ParameterDescriptor(++index, "usersParamDWord1", 4),// Пользовательский параметр 4 байта №1
                new ParameterDescriptor(++index, "usersParamDWord2", 4),// Пользовательский параметр 4 байта №2
                new ParameterDescriptor(++index, "usersParamDWord3", 4),// Пользовательский параметр 4 байта №3
                new ParameterDescriptor(++index, "usersParamDWord4", 4),// Пользовательский параметр 4 байта №4
                new ParameterDescriptor(++index, "usersParamDWord5", 4),// Пользовательский параметр 4 байта №5
                new ParameterDescriptor(++index, "usersParamDWord6", 4),// Пользовательский параметр 4 байта №6
                new ParameterDescriptor(++index, "usersParamDWord7", 4),// Пользовательский параметр 4 байта №7
                new ParameterDescriptor(++index, "usersParamDWord8", 4),// Пользовательский параметр 4 байта №8
                new ParameterDescriptor(++index, "usersParamDWord9", 4),// Пользовательский параметр 4 байта №9
                new ParameterDescriptor(++index, "usersParamDWord10", 4),// Пользовательский параметр 4 байта №10
                new ParameterDescriptor(++index, "usersParamDWord11", 4),// Пользовательский параметр 4 байта №11
                new ParameterDescriptor(++index, "usersParamDWord12", 4),// Пользовательский параметр 4 байта №12
                new ParameterDescriptor(++index, "usersParamDWord13", 4),// Пользовательский параметр 4 байта №13
                new ParameterDescriptor(++index, "usersParamDWord14", 4),// Пользовательский параметр 4 байта №14
                new ParameterDescriptor(++index, "usersParamDWord15", 4),// Пользовательский параметр 4 байта №15
                new ParameterDescriptor(++index, "usersParamQWord1", 8),// Пользовательский параметр 8 байт №1
                new ParameterDescriptor(++index, "usersParamQWord2", 8),// Пользовательский параметр 8 байт №2
                new ParameterDescriptor(++index, "usersParamQWord3", 8),// Пользовательский параметр 8 байт №3
        };

        public static final ParameterDescriptor[] flexList =
                new ParameterDescriptor[flex10List.length + flex20List.length + flex30List.length];

        static {
                int start = 0;
                for (int n = 0; n < flex10List.length; n++) {
                        flexList[start++] = flex10List[n];
                }
                for (int n = 0; n < flex20List.length; n++) {
                        flexList[start++] = flex20List[n];
                }
                for (int n = 0; n < flex30List.length; n++) {
                        flexList[start++] = flex30List[n];
                }
        }
}