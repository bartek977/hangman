  (function() {
    const API_URL = 'http://localhost:8081/api';
    const TOP_PLAYER_API_URL = `${API_URL}/top`;

    const ranking = document.getElementById('top-players');

    fetch(TOP_PLAYER_API_URL)
      .then(processOkResponse)
      .then(players => players.forEach(add_player_to_table));

var topPlayerNumber = 1;

    function add_player_to_table(player) {
        var table = document.getElementById("top-table");
        var row = table.insertRow(-1);
        var lp_cell = row.insertCell(0);
        var nick_cell = row.insertCell(1);
        var points_cell = row.insertCell(2);
        lp_cell.appendChild(document.createTextNode(topPlayerNumber));
        nick_cell.appendChild(document.createTextNode(player.username));
        points_cell.appendChild(document.createTextNode(player.points));
        topPlayerNumber++;
    }

    function processOkResponse(response = {}) {
      if (response.ok) {
        return response.json();
      }
      throw new Error(`Status not 200 (${response.status})`);
    }
  })();