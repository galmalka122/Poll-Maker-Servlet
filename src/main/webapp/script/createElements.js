export const createElements = (function () {


    const radioButton = function (index, answer) {

        return `<div>
                    <input class="form-check-input" type="radio" name="answers" id="answer-${index}" value="${answer}">
                    <label class="form-check-label" for="answer-${index}">
                        ${answer}
                    </label>
                </div>`
    }

    const addAnswers = function (answers) {
        let answersHtml = '';
        answers.forEach((ans, index) => {
            answersHtml += radioButton(index + 1, ans);
        });
        return answersHtml;
    }

    const buildPoll = function (json) {
        let poll = `<form method="post" action="/poll" class="poll-form">
            <h6 class="border-bottom pb-2 mb-0">${json.question}</h6>
            <div class="d-flex text-muted pt-3">
<div class="answers">`
        poll += addAnswers(json.answers);
        poll += `</div>
            </div>
            </form>`;
        return poll;
    }

    const addFormsButtons = function () {
        return `<button type="submit" class="btn btn-secondary poll-submit">Vote</button>
    <button type="reset" class="btn btn-secondary poll-reset">Reset</button>`;
    }

    const chartBuilder = (json)=>{
        let sum = 0;
        json.sort((a,b)=>{ return ((a.votes > b.votes) ? -1 : ((a.votes < b.votes) ? 1 : 0)); })
        if(json[0].votes == 0){
            sum = 1;
        }
        for (let i = 0;i < json.length; i++) {
            sum += json[i].votes;
        }

        let chart = `<table class="table table-striped table-hover"><thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">answer</th>
        <th scope="col">votes</th>
        <th scope="col">percentage</th>
    </tr>
    </thead>
    <tbody>`

        for(let i = 0;i<json.length;i++){
            let prec = (json[i].votes / sum) * 100
            if(!Number.isInteger(prec))prec = prec.toFixed(1)
            chart += `<tr>
      <th scope="row">${i + 1}</th>
      <td>${json[i].answer}</td>
      <td>${json[i].votes}</td>
      <td>${prec + '%'}</td>
    </tr>`
        }
        chart += `</tbody></table>`
        return chart
    }

    return {
        chartBuilder: chartBuilder,
        buildPoll: buildPoll,
        addFormsButtons: addFormsButtons
    }


}).call(this)
