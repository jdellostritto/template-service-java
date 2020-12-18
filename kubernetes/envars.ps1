Write-Host "Setting environment variables...";
$env:REPO="acme.io"
$env:ENVIRONMENT="default"
$env:BUILD="latest"
$env:REPCOUNT="1"
$env:PROJECT="template-service-java"
$env:DB_CONNECTION_STRING="jdbc:postgresql://postgres:5432/sample"
$env:DB_USER="sampleuser"
$env:DB_PWD="samplepass"
