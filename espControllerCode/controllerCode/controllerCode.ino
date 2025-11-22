//librarys for esp now protocol
#include "ESP32_NOW.h" //esp now library
#include "WiFi.h" //wifi library

//broadcast to the recievers mac address
uint8_t broadcastAddress[] = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF};

//structure of the data being sent
struct controllerState{
  int angle;
  strin movement;
};

//create a controllerState type which will be used to send the values to the reciever
controllerState dataToSend;

esp_now_peer_info_t peerInfo;

//button pins 
const int xPin = 34; //this is the pin that read the c input
const int yPin = 35; //this is the pin that read the y input 

//rpotentiomter read pin
const int potentiometerReadPin = 32;

//led pins
int blueLedPin = 17;
int redLedPin = 16;
int yellowLedPin = 18; //needs to be changed 
int greenLedPin = 19; //needs to be changed

//led states 
bool blueState = false;
bool redState = false;
bool yellowState = false;
bool greenState = false;

//variables for the state ofthe x and y positioin
int xValue = 0;
int yValue = 0;

//potentiometer read variable
int potentiometerValue;
int angle;

void setup() {
  //pins for the leds
  pinMode(blueLedPin,OUTPUT);
  pinMode(redLedPin,OUTPUT);
  pinMode(greenLedPin,OUTPUT);
  pinMode(yellowLedPin,OUTPUT);

  // turn on the serial monitor
  Serial.begin(115200);

  //set the device as a Wi-Fi statiom

}

void loop() {
  //read the values from the xy pins
  xValue = analogRead(xPin);
  yValue = analogRead(yPin);

  //read the value from the potentiometer
  potentiometerValue = analogRead(potentiometerReadPin);
  angle = getAngle(potentiometerValue);

  if(xValue> 2000){
    digitalWrite(blueLedPin,HIGH);
    digitalWrite(redLedPin,LOW);
  }else if(xValue < 1600){
    digitalWrite(blueLedPin,LOW);
    digitalWrite(redLedPin,HIGH);
  }else{
    digitalWrite(blueLedPin,LOW);
    digitalWrite(redLedPin,LOW);
  } 

  if(yValue > 2000){
    digitalWrite(yellowLedPin,HIGH);
    digitalWrite(greenLedPin,LOW);
  }else if(yValue <1000){
    digitalWrite(yellowLedPin,LOW);
    digitalWrite(greenLedPin,HIGH);
  }else{
    digitalWrite(yellowLedPin,LOW);
    digitalWrite(greenLedPin,LOW);
  }

  //print the value of the pin
  //Serial.println(xValue);
  //Serial.println(yValue);

  //read the voltage
  Serial.println(angle);

  delay(100);
}


//function to get the angle corresponding to the voltage from the potentiometer

int getAngle(int value){
  int angle;
  angle = 180-(value-2000)/11.25;
  if(angle >180){
    angle = 180;
  }if(angle < 0){
    angle = 0;
  }
  return angle;
}
