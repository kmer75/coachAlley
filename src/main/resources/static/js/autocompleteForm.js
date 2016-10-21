var placeSearch, autocomplete;

function initAutocomplete() {

    autocomplete = new google.maps.places.Autocomplete(
        (document.getElementById('autocomplete')),
        {
            types: ['geocode'],
            componentRestrictions: {country: 'fr'}
        });

    autocomplete.addListener('place_changed', fillInAddress);
}

function fillInAddress() {

    var place = autocomplete.getPlace();

    num = place.address_components[0].long_name;
    console.log(num);
    rue = place.address_components[1].long_name;
    console.log(rue);
    city = place.address_components[2].long_name;
    console.log(city);
    country = place.address_components[5].long_name;
    console.log(country);
    zipcode = place.address_components[6].long_name;
    console.log(zipcode);
    lat = place.geometry.location.lat();
    console.log(lat);
    lng = place.geometry.location.lng();
    console.log(lng);
    $('#address').val(num + ' ' + rue);
    $('#city').val(city);
    $('#country').val(country);
    $('#zipcode').val(zipcode);
    $('#geoLat').val(lat);
    $('#geoLong').val(lng);


}

function geolocate() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            var geolocation = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };
            var circle = new google.maps.Circle({
                center: geolocation,
                radius: position.coords.accuracy
            });
            autocomplete.setBounds(circle.getBounds());
        });
    }
}