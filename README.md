## 0x01 简介
`log4j-payload-generator`是 [woodpecker框架](https://github.com/woodpecker-framework/woodpecker-framwork-release/releases) 生产log4 jndi注入漏洞payload的插件。目前可以一键生产以下5类payload。

1. 原始payload
2. {[upper|lower]:x}类型随机混payload
3. {[upper|lower]:x}全混淆payload
4. {::-n}类型随机混淆payload
5. {::-n}类型全混淆payload

![log4j JNDI注入插件payload生成展示](./docs/log4j-payload-generator.png)

## 0x02 参考
* https://github.com/whwlsfb/Log4j2Scan