import CapstoneClient from '../api/CAPSTONECLIENT';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/*
The code below this comment is equivalent to...
const EMPTY_DATASTORE_STATE = {
    'search-criteria': '',
    'search-results': [],
};

...but uses the "KEY" constants instead of "magic strings".
The "KEY" constants will be reused a few times below.
*/

const SEARCH_CRITERIA_TITLEM = 'title';
const SEARCH_CRITERIA_DIRECTORM = 'director';
const SEARCH_RESULTS_KEY = 'search-results';
const EMPTY_DATASTORE_STATE = {
    [SEARCH_CRITERIA_TITLEM]: '',
    [SEARCH_CRITERIA_DIRECTORM]: '',
   [SEARCH_RESULTS_KEY]: '',
};

/**
 * Logic needed for the view itinerary page of the website.
 */
class GetMovie extends BindingClass {
    constructor() {
        super();

        this.bindClassMethods(['mount', 'search', 'displaySearchResults', 'getHTMLForSearchResults'], this);

        // Create a enw datastore with an initial "empty" state.
        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
        this.header = new Header(this.dataStore);
        this.dataStore.addChangeListener(this.displaySearchResults);
    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */
    mount() {
        // Wire up the form's 'submit' event and the button's 'click' event to the search method.
        document.getElementById('search-movies-form').addEventListener('submit', this.search);
        document.getElementById('movie-button').addEventListener('click', this.search);

        this.header.addHeaderToPage();

        this.client = new CapstoneClient();
    }

    /**
     * Uses the client to perform the search,
     * then updates the datastore with the criteria and results.
     * @param evt The "event" object representing the user-initiated event that triggered this method.
     */
    async search(evt) {
        // Prevent submitting the form from reloading the page.
        evt.preventDefault();

        const title = document.getElementById('title').value;
        const director = document.getElementById('director').value;

        if (title && director) {
            const results = await this.client.getMovie(title, director);
            this.dataStore.setState({
                [SEARCH_CRITERIA_TITLEM]: title,
                  [SEARCH_CRITERIA_DIRECTORM]: director,
                [SEARCH_RESULTS_KEY]: results,
            });
        } else {
            this.dataStore.setState(EMPTY_DATASTORE_STATE);
        }
    }

    /**
     * Pulls search results from the datastore and displays them on the html page.
     */
    async displaySearchResults() {
        const title = await this.dataStore.get(SEARCH_CRITERIA_TITLEM);
        const director = await this.dataStore.get(SEARCH_CRITERIA_DIRECTORM);
        const searchResult = await this.dataStore.get(SEARCH_RESULTS_KEY);

        const searchResultsContainer = document.getElementById('search-results-container');
        const searchCriteriaDisplay = document.getElementById('search-criteria-display');
        const searchResultsDisplay = document.getElementById('search-results-display');

        if (title === '' && director === '') {
            searchResultsContainer.classList.add('hidden');
            searchCriteriaDisplay.innerHTML = '';
            searchResultsDisplay.innerHTML = '';
        } else {
            searchResultsContainer.classList.remove('hidden');
            searchCriteriaDisplay.innerHTML = `"${title}" + "${director}"`;
          //  searchCriteriaDisplay.innerHTML = `"${director}"`;
            searchResultsDisplay.innerHTML = await this.getHTMLForSearchResults(searchResult);
        }
        document.getElementById("search-movies-form").reset();
    }

    /**
     * Create appropriate HTML for displaying searchResults on the page.
     * @param searchResults An array of playlists objects to be displayed on the page.
     * @returns A string of HTML suitable for being dropped on the page.
     */
    async getHTMLForSearchResults(searchResult) {
            if (searchResult === undefined){
                         return '<h4>No results found</h4>';
             }

     let html = '<table><tr><th>Title</th><th>Director</th></tr>';
                if ((searchResult.title != title) || (searchResult.director != director)) {
                                    html += `
                                    <tr>
                                        <td>${searchResult.title}
                                        </td>
                                         <td>${searchResult.director}</td>
                                          </tr>`;
                                }
                                html += '</table>';

                                return html;
                }
                            }

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const getMovie = new GetMovie();
    getMovie.mount();
};

window.addEventListener('DOMContentLoaded', main);

