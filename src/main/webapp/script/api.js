import {createElements} from './createElements.js';
import {validators} from './validators.js';
import {functions} from "./functions.js";

export const api = (function () {


        const voteFetch = async function (event) {
            const pollElement = document.querySelector('.poll-content');
            event.preventDefault();
            functions.loader('ON');
            let body = null;
            let url = '/poll';
            let answer = document.querySelector("input[type='radio']:checked");
            if (await validators.validateInput(() => validators.isEmpty(answer.value))){
                if(!(await validators.validateInput(()=>validators.cookieCheck('voted')))){
                    url = '/results';
                }
                else
                    body = new URLSearchParams({answer: answer.value}).toString();
                const result = await fetch(url, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                        'datatype': 'json'
                    },
                    body: body
                });
                await functions.fetchStatus(result, () => functions.chartFetchHandler(result.status));
                const chart = await result.json();
                pollElement.innerHTML = createElements.chartBuilder(chart);
                functions.loader();
            }

        }
        return {
            voteFetch: voteFetch
        }
    }
).call(this)
