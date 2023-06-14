# Practice_Project_Vendors
Project about working with data files in cvs format. Real world example.
Done in 7 hours
## Tasks
1. The program receives as input the name of the root directory where the "new_data" folder is located
2. The program should scan the network folder every 10 seconds and check for new files in it named RE_FRAUD_LIST_yyyyMMdd_000000_00000.txt

3. All new files must be processed in the following order:

- Generate new files from the source files and save them in a folder with the name of the vendor("оператор") located in the processed_data folder

- Each new file has the specified name VENDOR_FRAUD_LIST_yyyyMMdd_`*`.txt, where `*` is the serial number of the file in the vendor folder

- Information from the required file is entered into each new file by date, data clarification,only calls with FRAUD are added, grouping of data for each vendor SEPARATELY


3. All processed files from the "new_data" folder are moved to the "processed_data\processed" folder. 
If the folder already contains files with this name names, then there are files with the specified names according to the pattern file_name (number_starting_from_one).txt

## Example
### as is
![img.png](img.png)
## to be
![img.png](img.png)