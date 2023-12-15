const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,

  configureWebpack: {},
    devServer: { // 环境配置
      host: '0.0.0.0',
      port: '7000',
      https: false,
      /* disableHostCheck: true, */
      historyApiFallback: true,
      allowedHosts: "all",
      open: false // 配置自动启动浏览器
    }
})
