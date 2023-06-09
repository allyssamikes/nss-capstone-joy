import CapstoneClient from '../api/CAPSTONECLIENT';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";


const SEARCH_CRITERIA_KEY = 'search-criteria';
const SEARCH_RESULTS_KEY = 'search-results';
const EMPTY_DATASTORE_STATE = {
    [SEARCH_CRITERIA_KEY]: '',
    [SEARCH_RESULTS_KEY]: [],
};


/**
 * Logic needed for the view playlist page of the website.
 */
class SearchBooks extends BindingClass {
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
        document.getElementById('search-books-form').addEventListener('submit', this.search);
        document.getElementById('book-button').addEventListener('click', this.search);

        this.header.addHeaderToPage();

        this.client = new CapstoneClient();
    }

    /**
     * Uses the client to perform the search,
     * then updates the datastore with the criteria and results.
     * @param evt The "event" object representing the user-initiated event that triggered this method.
     */
    async search(evt) {
        // Prevent submitting the from from reloading the page.
        evt.preventDefault();

        const author = document.getElementById('search-criteria').value;

        console.log(" search books59");
        const results = await this.client.searchBooks(author);
        console.log(results.toString()+"55");
            this.dataStore.setState({
                [SEARCH_CRITERIA_KEY]: author,
                [SEARCH_RESULTS_KEY]: results,
            });
        }

    /**
     * Pulls search results from the datastore and displays them on the html page.
     */
    displaySearchResults() {
        const searchCriteria = this.dataStore.get(SEARCH_CRITERIA_KEY);
        const searchResults = this.dataStore.get(SEARCH_RESULTS_KEY);

        const searchResultsContainer = document.getElementById('search-results-container');
        const searchCriteriaDisplay = document.getElementById('search-criteria-display');
        const searchResultsDisplay = document.getElementById('search-results-display');

        if (searchCriteria === '') {
            searchResultsContainer.classList.add('hidden');
            searchCriteriaDisplay.innerHTML = '';
            searchResultsDisplay.innerHTML = '';
        } else {
            searchResultsContainer.classList.remove('hidden');
            searchCriteriaDisplay.innerHTML = `"${searchCriteria}"`;
            searchResultsDisplay.innerHTML = this.getHTMLForSearchResults(searchResults);
        }
    }

    /**
     * Create appropriate HTML for displaying searchResults on the page.
     * @param searchResults An array of playlists objects to be displayed on the page.
     * @returns A string of HTML suitable for being dropped on the page.
     */
    getHTMLForSearchResults(searchResults) {
        if (searchResults === undefined) {
            return '<h4>No results found</h4>';
        }
        let html = '<table><tr><th>Title</th><th>Author</th><th>Isbn</th></tr>';
        for (const res in searchResults.models) {
        console.log(res);
            html += `
            <tr>
                 <p> Click on Title to Add To A List </p>
                    <a href="ADDBOOKTOLIST.html?isbn=${res.isbn}">${res.title}</a>
                <td>${res.author}</td>
                <td>${res.isbn}</td>
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
    const searchBooks = new SearchBooks();
    searchBooks.mount();
};

window.addEventListener('DOMContentLoaded', main);