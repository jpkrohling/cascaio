Flightplan = require('flightplan')
startedAt = new Date()
year = startedAt.getFullYear()
month = ("0"+(startedAt.getMonth()+1)).slice(-2)
day = ("0"+(startedAt.getDate())).slice(-2)
hour = ("0"+(startedAt.getHours())).slice(-2)
minute = ("0"+(startedAt.getMinutes())).slice(-2)
second = ("0"+(startedAt.getSeconds())).slice(-2)
releaseName = "#{year}-#{month}-#{day}-#{hour}-#{minute}-#{second}"

releaseTag = "cascaio-app-#{releaseName}"
plan = new Flightplan()

plan.briefing {
  debug: false
  destinations: {
    'staging': {
      host: 'localhost'
      username: 'jpkroehling'
      agent: process.env.SSH_AUTH_SOCK
    }
    'production': {
      host: 'boto'
      username: 'deployer'
      privateKey: '/home/jenkins/.ssh/id_rsa'
    }
  }
}

plan.local (local) ->
  files = local.exec('find dist', {silent: true})
  local.transfer(files, "/tmp/#{releaseTag}")

plan.remote (remote) ->
  remote.exec("cp -R /tmp/#{releaseTag}/ /usr/share/nginx/html/app/")
  remote.exec("if [ -L /usr/share/nginx/html/app/current ] ; then rm /usr/share/nginx/html/app/current ; fi ")
  remote.exec("ln -s  /usr/share/nginx/html/app/#{releaseTag} /usr/share/nginx/html/app/current")
  remote.exec("sudo /bin/systemctl restart nginx", {exec: {pty: true}})
  remote.rm("-rf /tmp/#{releaseTag}")
