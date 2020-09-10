O projeto é uma demo da biblioteca Resilience4j.
Foi implementado um exemplo de gerenciamento a tolerância de falhas para comunicações remotas.



#### registrationService

Serviço que possui dois endpoints, um para  realizar o registro de um 'seller' e outro para buscar todos os registros.

 >@PostMapping("/addSeller")
 
 >@GetMapping("/sellersList")
 
O serviço está na porta 8086 e pode ser acessado com o swagger na seguinte url:
```sh
http://localhost:8086/swagger-ui.html
```

#### orderManagementApp

Serviço onde está implementando o resilience4j, é atraves dele que é feita a chamada do micro serviço de registro. O POST realiza o cadastro de um seller atraves do serviço que está na porta 8085, mesma situação do GET, que busca no serviço anterior todos os registros. Consequentemente o orderManagementApp precisa do registrationService para funcionar corretamente, foi implementado então nos dois metodos descritos, tratativas para cenarios de indisponibilidades do serviço presente na porta 8085.

OrderManagementAppApplication sobe na porta 8085 e pode ser acessado com o swagger na seguinte url:
```sh
http://localhost:8085/swagger-ui.html
```
![ordermanapp](https://user-images.githubusercontent.com/45246027/92658386-dd673480-f2cc-11ea-9a18-4101b01eac04.png)

#### Prometheus

É possivel monitorar os dados atraves de metricas em tempo real no prometheus, é necessário instalar o pacote do prometheus e alterar seu arquivo de configuração adicionando o trecho abaixo:

```python
  - job_name: 'spring-resilience4j'
    metrics_path: '/actuator/prometheus'
    scrape_interval: 5s    
    static_configs:
    - targets: ['127.0.0.1:8085']
```
Métricas interessantes para monitoramento : 

> resilience4j_circuitbreaker_state
> resilience4j_retry_calls



> Referencia técnica : https://resilience4j.readme.io/docs

