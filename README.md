# payara-evaluation

The Payara Micro evaluation.

## Objective

This repository is created with purpose to evaluate the **Payara** and 
**Payara Micro**, especially focusing on the **Eclipse: MicroProfile** 
compatibility. Not only evaluation, but also to make it as a 
`reproducible test case` for submitting the issue to the **Payara** as well.

### Concepts

This repository has tried to keep it simple and stupid as much as I can so that
I'm able to focus the topic one-by-one. Then there must not be neither 
mix and match among various tools nor complicated use cases here.

* [KISS principle](https://en.wikipedia.org/wiki/KISS_principle)
* [How to create a Minimal, Complete, and Verifiable example](https://stackoverflow.com/help/mcve)
* [Short, Self Contained, Correct (Compilable), Example](http://sscce.org/)
* [I downvoted because](http://idownvotedbecau.se/)

### The Roadmap

Since the **Eclipse: MicroProfile** is supported by various container vendors,
then there may be a chance that those new container may be added to this 
repository or use this repository as a base line for evaluating other containers.

### Target Execution

To make this repository to be portable enough, all execution must be done via the
command line by using the **Apache Maven** as a build tool.

## The knowledge

###  The Payara Micro with Arquillian

* [How to Test Applications with Payara® Server & Micro with Arquillian](https://blog.payara.fish/how-to-test-applications-with-payara-server-micro-with-arquillian)

* [Payara Micro Documentation](https://docs.payara.fish/documentation/payara-micro/payara-micro.html)
* [Payara: Eclipse MicroProfile](https://docs.payara.fish/documentation/microprofile/)
* [Payara Ecosystem](https://docs.payara.fish/documentation/ecosystem/ecosystem.html)
