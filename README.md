# Automated Analysis APP

This is the Automated Analysis APP (for my demos in OpenShift), which is a simplified version of the [quarkuscoffeeshop APP](https://github.com/quarkuscoffeeshop).

This repository just point to the microservices repos (GIT submodules), a repository with support files (ie. docker-compose for local development) and a Helm Chart to deploy it into OpenShift.

Those microservices are dependant in another repository containing the "domain" files, which are included by using a GIT submodule that "mounts" the [analysis-domain](https://github.com/luisarizmendi/analysis-domain) repository in 'src/main/java/analysis/domain'


The easier way to deploy the app in OpenShift would be to add my [Helm repo](https://github.com/luisarizmendi/helm-chart-repo) and the use the OpenShift Web console to deploy the app.

_NOTE_: To include my Helm repository in OpenShift, just create this object:

```
apiVersion: helm.openshift.io/v1beta1
kind: HelmChartRepository
metadata:
  name: larizmen-helm-repo
spec:
  name: Luis Arizmendi Helm Charts
  connectionConfig:
    url: https://raw.githubusercontent.com/luisarizmendi/helm-chart-repo/master/packages
```
