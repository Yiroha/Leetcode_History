$(function() {
  var FADE_TIME = 150; // ms
  var COLORS = [
    '#e21400', '#91580f', '#f8a700', '#f78b00',
    '#58dc00', '#287b00', '#a8f07a', '#4ae8c4',
    '#3b88eb', '#3824aa', '#a700ff', '#d300e7'
  ];

  // Initialize variables
  var $window = $(window);
  var $messages = $('.messages'); // 消息区域
  var $inputMessage = $('.inputMessage'); // 消息框

  // WebSocket
  var ws = new WebSocket("ws://localhost:3085");

  // Prompt for setting a username
  var connected = false; // 连接状态
  var $currentInput = $inputMessage.focus();

  // 输出日志信息
  function log (message, options) {
    var $el = $('<li>').addClass('log').text(message);
    addMessageElement($el, options);
  }

  // 输出聊天信息
  function addChatMessage (data, options) {
    options = options || {};
    var $usernameDiv = $('<span class="username"/>')
      .text(data.username + " ")
      .css('color', getUsernameColor(data.username));
    var $messageBodyDiv = $('<span class="messageBody">')
      .text(data.message);
    var $timeBodyDiv = $('<span class="timeBody">')
        .text(data.time + " ");

    var typingClass = data.typing ? '' : 'typing';
    var $messageDiv = $('<li class="message"/>')
      .data('username', data.username)
      .addClass(typingClass)
      .append($timeBodyDiv,$usernameDiv, $messageBodyDiv);

    addMessageElement($messageDiv, options);
  }

    // 输出群聊聊天信息
    function addGroupChatMessage (data, options) {
        options = options || {};
        var $groupDiv = $('<span class="group"/>')
            .text(data.group + " ")
            .css('color', getUsernameColor(data.group));
        var $usernameDiv = $('<span class="username"/>')
            .text(data.username + " ")
            .css('color', getUsernameColor(data.username));
        var $messageBodyDiv = $('<span class="messageBody">')
            .text(data.message);
        var $timeBodyDiv = $('<span class="timeBody">')
            .text(data.time + " ");


        var typingClass = data.typing ? '' : 'typing';
        var $messageDiv = $('<li class="message"/>')
            .data('username', data.username)
            .addClass(typingClass)
            .append($timeBodyDiv, $groupDiv, $usernameDiv, $messageBodyDiv);

        addMessageElement($messageDiv, options);
    }

    // 输出聊天信息
    function showChatMessage (data, options) {
        options = options || {};
        var $timeDiv = $('<span class="time"/>')
            .text(data.time)
        var $messageBodyDiv = $('<span class="messageBody">')
            .text(data.message);
        var typingClass = data.typing ? '' : 'typing';
        var $messageDiv = $('<li class="message"/>')
            .data('time', data.time)
            .addClass(typingClass)
            .append($timeDiv, $messageBodyDiv);

        addMessageElement($messageDiv, options);
    }

  // DOM 操作
  function addMessageElement (el, options) {
    var $el = $(el);

    if (!options) {
      options = {};
    }
    if (typeof options.fade === 'undefined') {
      options.fade = true;
    }
    if (typeof options.prepend === 'undefined') {
      options.prepend = false;
    }

    if (options.fade) {
      $el.hide().fadeIn(FADE_TIME);
    }
    if (options.prepend) {
      $messages.prepend($el);
    } else {
      $messages.append($el);
    }
    $messages[0].scrollTop = $messages[0].scrollHeight;
  }

  // 清除输入框中注入的信息
  function cleanInput (input) {
    return $('<div/>').text(input).html();
  }

  // 通过 hash 函数给用户名上色
  function getUsernameColor (username) {

    var hash = 4;
    for (var i = 0; i < username.length; i++) {
       hash = username.charCodeAt(i) + (hash << 5) - hash;
    }
    // 计算颜色下标
    var index = Math.abs(hash % COLORS.length);
    return COLORS[index];
  }

  // Keyboard events

  $window.keydown(function (event) {
    // 回车后依旧获取焦点
    if (!(event.ctrlKey || event.metaKey || event.altKey)) {
      $currentInput.focus();
    }
    // 监听回车键
    if (event.which === 13) {
        sendMessage();
    }
  });

  // 建立连接的时候更新连接状态
  ws.onopen = function (e) {
    console.log('Connection to server opened');
    log('已连接至服务器');
    connected = true;
  }

  // 处理服务器发送过来的消息
  ws.onmessage = function(e) {
    var msg = JSON.parse(e.data);
    console.log(msg);
    if(msg.err != 0){
        log(msg.message);
    }
    switch(msg.type) {
      case 1:
        // 广播通知
        log(msg.date + " " + msg.fromUsername + ":" + msg.message);
        break;
      case 2:
          // 收到其他人发过来的消息
          var data = {
              username: msg.fromUsername,
              message: msg.message,
              time: msg.time
          };
          addChatMessage(data);
        break;
      case 5:
        // 收到群组发过来的消息
        var data = {
            group: msg.group,
            username: msg.fromUsername,
            message: msg.message,
            time: msg.time
        };
        addGroupChatMessage(data);
        break;
    }
  }

  ws.onclose = function(e) {
    connected = false;
  }

  ws.onerror = function(e) {
    connected = false;
  }

  // 发送消息
  function sendMessage() {
    if(connected) {
      var date = new Date().Format("M-D");
      var time = new Date().Format("H:m:S");
      var msg = {};
      var command = cleanInput($inputMessage.val()).split("/");
      if(command.length > 1){
        switch (command[0]){
            case "C":
              //创建群聊
              var str = command[1].split(":");
                if(str.length > 1) {
                    msg.message = str[1];
                }
              msg.type = 4;
              msg.group = str[0];
              break;
            // case "J":
            //   //加入群聊
            //   msg.type = 5;
            //   msg.group = command[1];
            //   break;
            // case "E":
            //   //退出群聊
            //   msg.type = 6;
            //   msg.group = command[1];
            //   break;
            case "S":
              //单聊
              var str = command[1].split(":");
              if(str.length > 1) {
                  msg.message = str[1];
              }
              msg.toUsername = str[0];
              msg.type = 2;
              break;
            case "G":
              //群聊
              var str = command[1].split(":");
              if(str.length > 1){
                  msg.message = str[1];
              }
              msg.group = str[0];
              msg.type = 5;
              break;
            case "A":
              //广播通知
              msg.type = 1;
              if(command[1] != ""){
                  msg.message = command[1];
              }
        }
      }else{
          //上一次会话
          msg.type = 3;
          msg.message = command[0];
      }
      msg.date = date;
      msg.time = time;
      if(msg.type != null || msg.message != null){
          ws.send(JSON.stringify(msg));
          if(msg.type != 1 && msg.message != null){
              var timedis = new Date().Format("H:m:S ");
              showChatMessage({time:timedis,message:msg.message});
          }
          $inputMessage.val("");
      }
    }else {
      log("与服务器断开连接了，刷新重新连接~");
    }
  }
    Date.prototype.Format = function(formatStr)  {
        var str = formatStr;

        str=str.replace(/M/g,this.getMonth() + 1);

        str=str.replace(/d|D/g,this.getDate());

        str=str.replace(/h|H/g,this.getHours());
        str=str.replace(/m/g,this.getMinutes());
        str=str.replace(/s|S/g,this.getSeconds());

        return str;
    }
});