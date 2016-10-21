
$( document ).ready( function () {

    $( "#signupForm" ).validate( {
        rules: {
            lastname: {
                required: true,
                minlength: 2
            },
            firstname: {
                required: true,
                minlength: 2
            },
            password: {
                required: true,
                minlength: 6
            },
            confirmPassword: {
                required: true,
                minlength: 6,
                equalTo: "#password"
            },
            email: {
                required: true,
                email: true
            },
            address: {
                required: true,
            },
            zipcode: {
                required: true,
            },
            country: {
                required: true,
            },
            city: {
                required: true,
            },
        },
        messages: {

            lastname: {
                required: "Veuillez renseigner votre nom",
                minlength: "doit contenir minimum 2 caractères"
            },
            firstname: {
                required: "Veuillez renseigner votre prénom",
                minlength: "doit contenir minimum 2 caractères"
            },
            password: {
                required: "Saisissez votre mot de passe",
                minlength: "Le mot de passe doit contenir au moins 6 caractères"
            },
            confirm_password: {
                required: "Saisissez à nouveau votre mot de passe",
                minlength: "Le mot de passe doit contenir au moins 6 caractères",
                equalTo: "doit être équivalent au mot de passe saisis"
            },
            email: "Veuillez renseigner votre adresse email",
            address: "Veuillez sélectionner votre adresse",
            zipcode: "Veuillez sélectionner votre adresse",
            city: "Veuillez sélectionner votre adresse",
            country: "Veuillez sélectionner votre adresse",
        },
        errorElement: "em",
        errorPlacement: function ( error, element ) {
            // Add the `help-block` class to the error element
            error.addClass( "help-block" );

            if ( element.prop( "type" ) === "checkbox" ) {
                error.insertAfter( element.parent( "label" ) );
            } else {
                error.insertAfter( element );
            }
        },
        highlight: function ( element, errorClass, validClass ) {
            $( element ).parents( ".light" ).addClass( "has-error" ).removeClass( "has-success" );
        },
        unhighlight: function (element, errorClass, validClass) {
            $( element ).parents( ".light" ).addClass( "has-success" ).removeClass( "has-error" );
        }
    } );
    
    $( "#checkEmailExist" ).validate( {
        rules: {
            email: {
                required: true,
                email: true
            },
        },
        messages: {
            email: "Veuillez rentrer un email valide"
        },
        errorElement: "em",
        errorPlacement: function ( error, element ) {
            // Add the `help-block` class to the error element
            error.addClass( "help-block" );

            if ( element.prop( "type" ) === "checkbox" ) {
                error.insertAfter( element.parent( "label" ) );
            } else {
                error.insertAfter( element );
            }
        },
        highlight: function ( element, errorClass, validClass ) {
            $( element ).parents( ".light" ).addClass( "has-error" ).removeClass( "has-success" );
        },
        unhighlight: function (element, errorClass, validClass) {
            $( element ).parents( ".light" ).addClass( "has-success" ).removeClass( "has-error" );
        }
    } );

    $( "#login" ).validate( {
        rules: {
            username: {
                required: true,
                email: true,
            },
            password: {
                required: true,
                minlength: 6
            },
        },
        messages: {
            username: {
                required: "Veuillez rentrer un email valide",
                email: "Veuillez rentrer un email valide",
            },
            password: {
                required: "Saisissez votre mot de passe",
                minlength: "Le mot de passe doit contenir au moins 6 caractères"
            },
        },
        errorElement: "em",
        errorPlacement: function ( error, element ) {
            // Add the `help-block` class to the error element
            error.addClass( "help-block" );

            if ( element.prop( "type" ) === "checkbox" ) {
                error.insertAfter( element.parent( "label" ) );
            } else {
                error.insertAfter( element );
            }
        },
        highlight: function ( element, errorClass, validClass ) {
            $( element ).parents( ".light" ).addClass( "has-error" ).removeClass( "has-success" );
        },
        unhighlight: function (element, errorClass, validClass) {
            $( element ).parents( ".light" ).addClass( "has-success" ).removeClass( "has-error" );
        }
    } );

    $( "#forgetPassword" ).validate( {
        rules: {
            password: {
                required: true,
                minlength: 6
            },
            confirmPassword: {
                required: true,
                minlength: 6,
                equalTo: "#password"
            },
        },
        messages: {
            password: {
                required: "Saisissez votre mot de passe",
                minlength: "Le mot de passe doit contenir au moins 6 caractères"
            },
            confirm_password: {
                required: "Saisissez à nouveau votre mot de passe",
                minlength: "Le mot de passe doit contenir au moins 6 caractères",
                equalTo: "doit être équivalent au mot de passe saisis"
            },
        },
        errorElement: "em",
        errorPlacement: function ( error, element ) {
            // Add the `help-block` class to the error element
            error.addClass( "help-block" );

            if ( element.prop( "type" ) === "checkbox" ) {
                error.insertAfter( element.parent( "label" ) );
            } else {
                error.insertAfter( element );
            }
        },
        highlight: function ( element, errorClass, validClass ) {
            $( element ).parents( ".light" ).addClass( "has-error" ).removeClass( "has-success" );
        },
        unhighlight: function (element, errorClass, validClass) {
            $( element ).parents( ".light" ).addClass( "has-success" ).removeClass( "has-error" );
        }
    } );

} );