# spring-security-jwt-sample
Spring Security × JWTによるREST APIの認証デモアプリケーション  
ユーザの権限別にAPI呼び出しを制限

|メソッド| パス                       | 権限  | 備考 |
|:------:|---------------------------|-------|--------------|
| GET| /api/vi/public/hello | なし | 未ログインで呼び出し可能 |
| GET| /api/vi/admin/find/{id} | ADMIN |利用者情報の取得|
| GET | /api/vi/admin/find/all | ADMIN |全利用者情報の取得              |
|POST  | /api/v1/admin/add         | ADMIN |利用者情報の登録|
|PUT   | /api/v1/admin/update      | ADMIN |利用者情報の変更|
|DELETE| /api/v1/admin/delete/{id} | ADMIN |利用者情報の削除|
|GET   | /api/v1/user/find/me      | USER  |自身の情報取得|
|PUT   | /api/v1/user/update/me      | USER  |自身の情報変更|
|DELETE   | /api/v1/user/delete/me      | USER  |自身の情報削除|

パラメータなどの詳細はswaggerで
http://localhost:8080/swagger-ui.html

DB
http://localhost:8080/h2-console
