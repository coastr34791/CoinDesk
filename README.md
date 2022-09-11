# Currency and CoinDesk API test

##### 1. Currency CRUD 相關在 CurrencyController
- /api/currency/create : 新增幣別。
- /api/currency/query : 查詢所有幣別，並回傳顯示。
- /api/currency/query/{code} : 查詢指定幣別，並回傳顯示。
- /api/currency/update : 更新指定幣別資訊，並回傳顯示。
- /api/currency/delete/{code} : 移除指定幣別。

##### 2. CoinDesk API 相關在 CoinDeskController
- /api/coindesk/call : 呼叫原CoinDesk API，並回傳顯示。
- /api/coindesk/trans : 資料轉換，指定時間格式、幣別相關名稱匯率，並回傳顯示。
- /api/coindesk/update : 資料轉換，更新 Currency Table 相關資訊，並回傳顯示。

##### 3. 其他
- 測試使用 Swagger UI
- Unit Test 在 CoinDeskApplicationTests
 
