# K8s setup

## Commands Used

-

```
kubectl create deployment mysql --image=mysql:8.3.0 --port=3306 --replicas=1 --dry-run=client -o yaml
```

- `kubectl create service clusterip mysql --tcp=3306:3306 --dry-run=client -o yaml >> mysql.yaml`

- `kubectl create secret generic mysql-secrets --from-literal=mysql_root_password=mysql --dry-run=client -o yaml`
    - NOTE: just base64 encoded, will update to HVault implementation 