import {api} from "/script/api.js";
import {functions} from "/script/functions.js";
import {createElements} from "/script/createElements.js";

document.addEventListener('DOMContentLoaded', async (event) => {
    try{
        const pollElement = document.querySelector('.poll-content');
        const buttons = document.querySelector('.poll-buttons');
        functions.loader('ON');
        const result = await fetch('/poll');
        await functions.fetchStatus(result, () => functions.pollFetchHandler(result.status));
        const poll = await result.json();
        pollElement.innerHTML = createElements.buildPoll(poll);
        buttons.innerHTML = createElements.addFormsButtons();
        functions.attachListeners('click', '.poll-submit', async (event) =>{
            try{
                buttons.innerHTML = '';
                await api.voteFetch(event)
            }
            catch (err){
                functions.showModalErr(err);
            }
        });
        functions.attachListeners('click', '.poll-reset', async () => {
            document.querySelector('.poll-form').reset();
        });
        functions.loader();
    }
    catch (err){
        functions.loader();
        functions.showModalErr(err);
    }
    })



