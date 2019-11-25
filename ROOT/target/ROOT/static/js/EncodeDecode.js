function htmlEncodeJQ ( str ) {
    return $('<span/>').text( str ).html();
}

function htmlDecodeJQ ( str ) {
    return $('<span/>').html( str ).text();
}