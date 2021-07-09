# Automated Analysis APP

This is the Automated Analysis APP (for my demos in OpenShift), which is a simplified version of the [quarkuscoffeeshop APP](https://github.com/quarkuscoffeeshop).

This repository point to the microservices repos (GIT subtree), a repository with support files (ie. docker-compose for local development) and a Helm Chart to deploy it into OpenShift.

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
    url: https://raw.githubusercontent.com/luisarizmendi/helm-chart-repo/main/packages
```


## Subtrees

This repository includes subtrees, if you are working and there are new commits in the subtree that you want to get, you would need to run a command like this:

```
for i in analysis-support analysis-helm analysis-domain analysis-core analysis-gateway analysis-process-regular analysis-process-virus
do
  git subtree pull --prefix $i https://github.com/luisarizmendi/$i main --squash
done
```