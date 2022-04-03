export const functions = (() => {

    /**
     * checks the promise status for errors
     * @param response - the response from fetch
     * @param handler - the handler function
     * @returns {Promise<never>|Promise<unknown>} - returns the resolved promise if ok else returns error
     */
    const fetchStatus = async (response, handler = ()=>{}) => {
        if (response.status >= 200 && response.status < 300)
            return Promise.resolve(response);

        //handle the error status
        handler();
    }

    /**
     * attach listener for all queries
     * @param event - the event which the listener will attach to
     * @param query - the queries which the listener will attach to
     * @param listenerFunction - the function to run when event fired
     */
    const attachListeners = (event, query, listenerFunction) => {

        for (const b of document.querySelectorAll(`${query}`))
            b.addEventListener(event, listenerFunction);
    }


    /**
     * turns the spinner element on or off
     * @param on - null indicates to turn the spinner off, otherwise turn on
     */
    const loader = function (on = null) {

        const loaderNode = document.querySelector('.spinner-border.loader-center'); //the loader Node

        loaderNode.style.display = on ? 'block' : 'none'; //show/hide loader
        document.body.style.opacity = on ? '0.2' : '1.0'; //blur/brighten page body
    }

    /**
     * creates the information modal body by the error caught and opens it on the page
     * @param err - the body information
     */
    function showModalErr(err) {

        const modalERR = document.getElementById('err-modal'); // the error modal element
        document.getElementById('err-body').innerText = err; // plant the error string on the modal
        let modalInstance;
        modalInstance = new bootstrap.Modal(modalERR); // create the modal instance
        modalInstance.show(); // show the modal
    }


    const chartFetchHandler = function(status){
        if(status === 500){
            document.querySelector('.container').innerHTML = '';
            throw new Error("The poll is currently unavailable.\nPlease try again later");
        }
        if(status === 401)
            displayError('Your vote was not approved.\nYou can only vote once');

    }

    const pollFetchHandler = function (status) {
        if(status === 500){
            document.querySelector('.container').innerHTML = '';
            throw new Error("The poll is currently unavailable.\nPlease try again later")
        }
    }

    const displayError = function (err) {
        let errElement = document.querySelector('.error');
        errElement.style.display = 'block';
        errElement.innerHTML = err;
    }



    return {
        chartFetchHandler: chartFetchHandler,
        fetchStatus: fetchStatus,
        showModalErr: showModalErr,
        loader: loader,
        attachListeners: attachListeners,
        pollFetchHandler: pollFetchHandler,
        displayError: displayError
    }

}).call(this)