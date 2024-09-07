### 1.jsonサーバーを起動
```
node server.js
```
### 2.ngrokでローカルサーバーを公開
```
ngrok http http://localhost:3000
```

### 3.なんちゃってサーバー証明書の作り方
https://devcenter.heroku.com/ja/articles/ssl-certificate-self  
※作成してみたが、安全性が確保できないSSL証明書のため、Kotlinからローカルサーバーに対して、RestApiで送信してもはじかれる  
無料SSL証明書「Let's Encrypt」はドメインが必要なため試してない  