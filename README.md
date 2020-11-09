# WCF-OA
WCF insurance Online Assessment

Created the project to implement the cell phone usage report for company. 

* The application is runnable with SpringBoot with stdout logs but the pdf report part is not finished as trying to implement a new engine JasperReport which I'm not very familiar and render pages take much time
* Used the sample payload as the data source for the current demo. Note: In the actual application , data can be loaded from Database or Redis storage.
* Used an overall nested CellPhone for data representation which integrate with the cell phone usage as well. 
* Implemented algorithm to calculate and aggregate the cell phone usage with an array representation as followings, Month usage is shown from index 1 to index 12 represent Jan to Dec. 


> 1910=CellPhone(employeeId=1910, employeeName=Cleveland Brown, purchaseDate=Thu Dec 31 17:00:00 MST 2015, model=Apple iPhone 6, usage=Minutes Usage: {
  "2017" : [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 32, 473, 549, 286 ],
  "2018" : [ 0, 381, 435, 467, 450, 410, 378, 432, 655, 155, 0, 0, 0 ]
}, Data Usage: {
  "2017" : [ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 2.59, 8.94, 8.030001, 3.09 ],
  "2018" : [ 0.0, 7.87, 8.67, 13.51, 10.07, 8.92, 5.86, 9.719999, 12.540001, 2.81, 0.0, 0.0, 0.0 ]
}) <

* Once all data has been collected, we can build out the data model and inject into the JasperReport engine to render the page.
