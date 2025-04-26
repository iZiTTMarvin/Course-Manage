
/*
* 时间格式化
* */
export const formatDateTime = (date) => {
    if (date === "" || !date) {
        return "";
    }
    var date = new Date(date);
    var y = date.getFullYear();
    var m: string | number = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d: string | number = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var h: string | number = date.getHours();
    h = h < 10 ? ('0' + h) : h;
    var minute: string | number = date.getMinutes();
    minute = minute < 10 ? ('0' + minute) : minute;
    var second: string | number = date.getSeconds();
    second = second < 10 ? ('0' + second) : second;
    return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
}

/*
* 图标
* */

export const iconList = [
    {value: 'MuteNotification', label: 'MuteNotification'},
    {value: 'CirclePlus', label: 'CirclePlus'},
    {value: 'Plus', label: 'Plus'},
    {value: 'Minus', label: 'Minus'},
    {value: 'Search', label: 'Search'},
    {value: 'Female', label: 'Female'},
    {value: 'Male', label: 'Male'},
    {value: 'Aim', label: 'Aim'},
    {value: 'House', label: 'House'},
    {value: 'FullScreen', label: 'FullScreen'},
    {value: 'Loading', label: 'Loading'},
    {value: 'Link', label: 'Link'},
    {value: 'Service', label: 'Service'},
    {value: 'Pointer', label: 'Pointer'},
    {value: 'Star', label: 'Star'},
    {value: 'Notification', label: 'Notification'},
    {value: 'Connection', label: 'Connection'},
    {value: 'ChatDotRound', label: 'ChatDotRound'},
    {value: 'Setting', label: 'Setting'},
    {value: 'Clock', label: 'Clock'},
    {value: 'Position', label: 'Position'},
    {value: 'Discount', label: 'Discount'},
    {value: 'Odometer', label: 'Odometer'},
    {value: 'ChatSquare', label: 'ChatSquare'},
    {value: 'ChatRound', label: 'ChatRound'},
    {value: 'ChatLineRound', label: 'ChatLineRound'},
    {value: 'ChatLineSquare', label: 'ChatLineSquare'},
    {value: 'ChatDotSquare', label: 'ChatDotSquare'},
    {value: 'View', label: 'View'},
    {value: 'Hide', label: 'Hide'},
    {value: 'Unlock', label: 'Unlock'},
    {value: 'Lock', label: 'Lock'},
    {value: 'RefreshRight', label: 'RefreshRight'},
    {value: 'RefreshLeft', label: 'RefreshLeft'},
    {value: 'Refresh', label: 'Refresh'},
    {value: 'Bell', label: 'Bell'},
    {value: 'User', label: 'User'},
    {value: 'Check', label: 'Check'},
    {value: 'CircleCheck', label: 'CircleCheck'},
    {value: 'Warning', label: 'Warning'},
    {value: 'CircleClose', label: 'CircleClose'},
    {value: 'Close', label: 'Close'},
    {value: 'PieChart', label: 'PieChart'},
    {value: 'More', label: 'More'},
    {value: 'Compass', label: 'Compass'},
    {value: 'Filter', label: 'Filter'},
    {value: 'Switch', label: 'Switch'},
    {value: 'Select', label: 'Select'},
    {value: 'SemiSelect', label: 'SemiSelect'},
    {value: 'CloseBold', label: 'CloseBold'},
    {value: 'EditPen', label: 'EditPen'},
    {value: 'Edit', label: 'Edit'},
    {value: 'Message', label: 'Message'},
    {value: 'MessageBox', label: 'MessageBox'},
    {value: 'TurnOff', label: 'TurnOff'},
    {value: 'Finished', label: 'Finished'},
    {value: 'Delete', label: 'Delete'},
    {value: 'Crop', label: 'Crop'},
    {value: 'SwitchButton', label: 'SwitchButton'},
    {value: 'Operation', label: 'Operation'},
    {value: 'Open', label: 'Open'},
    {value: 'Remove', label: 'Remove'},
    {value: 'ZoomOut', label: 'ZoomOut'},
]


