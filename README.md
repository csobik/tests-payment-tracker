# tests-payment-tracker

Program for testing purposes that keeps a record of payments. Each payment includes a currency and an amount. Data
are kept only in memory.

The program outputs a list of all the currency and amounts to the console once per minute. The input can
be typed into the command line with possibility to be automated in the future, and optionally also can be loaded from
a file when starting up.

to run application:
```bash
 mvn spring-boot:run
 ```
 or 
```bash
  mvn clean -U package; java -jar target/payment-tracker-1.0.jar
 ```
 
 ---
 
 For passing init file append it as parameter:
 
 ```bash
 mvn spring-boot:run -Drun.arguments="--initFile=/Users/jirisobotik/Work/payment-tracker/InitFile.txt"
 ```
 
 ---
 
 ## possible operations
 
* __add__: add new payment record  
    `my-shell:>add USD 13` 
* __init__: initialize repository from given file 
     (can be set when application starts as console parameter or here as optional parametr)   
     `my-shell:>init` (when passed file from command line)  
     `my-shell:>init /Users/jirisobotik/Work/payment-tracker/InitFile.txt`  
* __sum__: summarize all payments grouped by currency   
      `my-shell:>sum`
* __supported-currencies__: print all supported currencies   
      `my-shell:>supported-currencies`


