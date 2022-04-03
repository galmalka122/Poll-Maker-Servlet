export const validators = (function () {

    /**
     * checks if value is empty
     * @param value
     * @returns {Promise<{isValid: boolean, message: string}>} - validation status + message
     */
    const isEmpty = async function (value) {
        return {
            isValid: value !== undefined && value !== '' && value !== null,
            message: 'Please select an answer'
        };
    }

    const cookieCheck = async function (value) {
        let cookies = document.cookie.split(";");
        let found = false;
        cookies.forEach((el)=>{
            if(el.split('=')[0] === value) {
                found = true;
            }
        })
        return {
            isValid: !found,
            message: 'Your vote was not approved.\nYou can only vote once'
        };
    }


    /** creates an error message if input isn't valid and returns the validation state
     * @param inputElement - the input value
     * @param validateFunc - the function for validation
     * @returns {boolean|*} - the final validation state
     */
    const validateInput = async (validateFunc) => {
        let errorElement = document.querySelector('.error'); // the error message div
        let v = await validateFunc()// call the validation function
        errorElement.innerHTML = v.isValid ? '' : v.message;// display the error message
        errorElement.style.display = v.isValid ? 'none' : 'block';
        return v.isValid;
    }



    return {
        isEmpty: isEmpty,
        cookieCheck: cookieCheck,
        validateInput: validateInput,
    }

}).call(this)
