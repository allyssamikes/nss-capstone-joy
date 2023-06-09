import CapstoneClient from '../api/CAPSTONECLIENT';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';


class RemoveBookFromList extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit'], this);
        this.dataStore = new DataStore();
        this.dataStoreSearch = new DataStore();
        this.header = new Header(this.dataStore);
        console.log("constructor")
    }
    /**
     * Add the header to the page and load the Client.
     */
    mount() {
        document.getElementById('remove-book').addEventListener('click', this.submit);
        this.header.addHeaderToPage();

        this.client = new CapstoneClient();
    }

        async submit(evt) {
            evt.preventDefault();

            const errorMessageDisplay = document.getElementById('error-message');
            errorMessageDisplay.innerText = ``;
            errorMessageDisplay.classList.add('hidden');

            const removeButton = document.getElementById('remove-book');
            const origButtonText = removeButton.innerText;
            removeButton.innerText = 'Loading...';

            const userId = document.getElementById('userId').value;
            const list = document.getElementById('list-type').value;
            const isbn =document.getElementById('isbn').value;
           console.log("54");
           console.log(isbn);

               if (list === "currentlyReading" ) {
                     await this.client.removeBookFromCurrentlyReading(userId, isbn, (error) => {
                            removeButton.innerText = origButtonText;
                            const errorMessageDisplay = document.getElementById('error-message');
                            errorMessageDisplay.innerText = `Error: ${error.message}`;
                            errorMessageDisplay.classList.remove('hidden');
                        });
                        }
              if (list === "toReadList") {
                      await this.client.removeBookFromToReadList(userId, isbn, (error) => {
                            removeButton.innerText = origButtonText;
                            const errorMessageDisplay = document.getElementById('error-message');
                            errorMessageDisplay.innerText = `Error: ${error.message}`;
                            errorMessageDisplay.classList.remove('hidden');
              });
                        }

            removeButton.innerText = 'Complete';
            removeButton.innerText = 'Removed';
        }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const removeBookFromList = new RemoveBookFromList();
    removeBookFromList.mount();
};

window.addEventListener('DOMContentLoaded', main);